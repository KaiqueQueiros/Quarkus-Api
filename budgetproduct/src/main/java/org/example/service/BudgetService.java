package org.example.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.ProductClient;
import org.example.dto.BudgetDTO;
import org.example.dto.BudgetUpdateDTO;
import org.example.dto.ProductDTO;
import org.example.entity.BudgetEntity;

import java.util.List;

@ApplicationScoped
public class BudgetService {
    @Inject
    @RestClient
    ProductClient productClient;

    public Response finAll() {
        List<BudgetEntity> findAll = BudgetEntity.findAll().list();
        if (findAll.isEmpty()) {
            return Response.status(404).entity("Lista de orçamentos vazia").build();
        }
        return Response.ok(findAll).build();
    }

    public void createBudget(BudgetDTO dto) {
        ProductDTO productDTO = productClient.getProductById(dto.getProductId());
        if (productDTO != null) {
            BudgetEntity.persist(new BudgetEntity(dto));
        } else
            throw new NotFoundException();
    }

    public Response deleteBudget(@PathParam("id") Long id) {
        PanacheEntityBase budget = BudgetEntity.findById(id);
        if (budget == null) {
            return Response.status(404).entity("Orçamento não encontrado").build();
        }
        budget.delete();
        return Response.status(200).entity("Orçamento excluido").build();
    }

    public Response updateBudget(@RequestBody BudgetUpdateDTO dto, @PathParam("id") Long id) {
        BudgetEntity budget = BudgetEntity.findById(id);
        if (budget == null) {
            return Response.status(404).entity("Orçamento não encontrado").build();
        }
        budget.update(dto);
        BudgetEntity.persist(budget);
        return Response.ok(budget).build();
    }

    private BudgetDTO mapEntityToDTO(BudgetEntity budgetEntity) {
        return new BudgetDTO(budgetEntity);
    }
}
