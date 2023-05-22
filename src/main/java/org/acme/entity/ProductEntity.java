package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.acme.dto.ProductDTO;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_product")
public class ProductEntity extends PanacheEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "culture")
    private String culture;
    @Column(name = "area")
    private String area;

    public ProductEntity(ProductDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.culture = dto.getCulture();
        this.area = dto.getArea();
    }
    public void update(ProductDTO dto) {
        if (dto.getArea() != null) {
            this.name = dto.getName();
        }
        if (dto.getDescription() != null) {
            this.description = dto.getDescription();
        }
        if (dto.getCulture() != null) {
            this.culture = dto.getCulture();
        }
        if (dto.getArea() != null) {
            this.area = dto.getArea();
        }
    }

}
