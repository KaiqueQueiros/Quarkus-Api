package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.service.ProductService;

import java.util.List;

@Path("/api/product")
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findAllProducts() {
        return productService.listAllProducts();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO getProductById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@Valid ProductDTO productDTO) {
        try {
            productService.createProcuct(productDTO);
            return Response.ok(productDTO).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductDTO dto) {
        ProductEntity product = ProductEntity.findById(id);
        if (product == null) {
            return Response.status(404).entity("Produto não encontrado").build();
        }
        productService.updateProduct(dto, id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id) {
        ProductEntity product = ProductEntity.findById(id);
        if (product == null) {
            return Response.status(404).entity("Produto não encontrado").build();
        }
        productService.deleteProduct(id);
        return Response.status(200).entity("Produto excluido").build();
    }

}