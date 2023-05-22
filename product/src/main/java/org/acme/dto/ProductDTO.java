package org.acme.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.ProductEntity;

@Data
@ApplicationScoped
@NoArgsConstructor
@Jacksonized
public class ProductDTO {
    private String name;
    private String description;
    private String culture;
    private String area;

    public ProductDTO(ProductEntity product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.culture = product.getCulture();
        this.area = product.getArea();
    }
}
