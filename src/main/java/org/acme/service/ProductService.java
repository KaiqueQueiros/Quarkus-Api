package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {
    public List<ProductDTO> listAllProducts() {
        List<ProductEntity> productEntities = ProductEntity.findAll().list();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            ProductDTO productDTO = new ProductDTO(product);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public void createProcuct(@Valid ProductDTO dto) {
        ProductEntity product = new ProductEntity(dto);
        product.persist();
    }

    public Response updateProduct(@Valid ProductDTO dto, @PathParam("id") Long id) {
        ProductEntity product = ProductEntity.findById(id);
        if (product == null) {
            return Response.status(404).entity("Produto não encontrado").build();
        }
        product.update(dto);
        ProductEntity.persist(product);
        return Response.ok(product).build();
    }

    public Response deleteProduct(@PathParam("id") Long id) {
        ProductEntity product = ProductEntity.findById(id);
        if (product == null) {
            return Response.status(404).entity("Produto não encontrado").build();
        }
        product.delete();
        return Response.ok("Produto excluido").build();
    }
}
