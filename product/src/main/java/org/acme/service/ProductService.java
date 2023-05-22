package org.acme.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
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

    public void createProcuct(ProductDTO dto) {
        ProductEntity product = new ProductEntity(dto);
        product.persist();
    }

    public void updateProduct(@Valid ProductDTO dto, @PathParam("id") Long id) {
        ProductEntity product = ProductEntity.findById(id);
        product.update(dto);
        ProductEntity.persist(product);
    }
    public ProductDTO findById(Long id) {
        ProductEntity byId = ProductEntity.findById(id);
        return new ProductDTO(byId);
    }
    private ProductDTO mapProductEntityToDTO(ProductEntity product) {
        return new ProductDTO(product);
    }


    public void deleteProduct(Long id) {
        ProductEntity.deleteById(id);
    }
}
