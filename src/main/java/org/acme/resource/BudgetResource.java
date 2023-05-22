package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.BudgetDTO;
import org.acme.dto.BudgetUpdateDTO;
import org.acme.service.BudgetService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/budget")
public class BudgetResource {
    @Inject
    BudgetService budgetService;

    @GET
    @RolesAllowed({"user","admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return budgetService.finAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createBudget(BudgetDTO dto) {
        budgetService.createBudget(dto);
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteBudget(@PathParam("id") Long id) {
        return budgetService.deleteBudget(id);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBudget(@RequestBody BudgetUpdateDTO dto, @PathParam("id") Long id) {
        return budgetService.updateBudget(dto, id);
    }
}
