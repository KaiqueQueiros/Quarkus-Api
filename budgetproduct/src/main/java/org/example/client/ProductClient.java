package org.example.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.ProductDTO;

@Path("/product")
@RegisterRestClient
@ApplicationScoped
public interface ProductClient {
    @GET
    @Path("/{id}")
    ProductDTO getProductById(@PathParam("id") Long id);
}
