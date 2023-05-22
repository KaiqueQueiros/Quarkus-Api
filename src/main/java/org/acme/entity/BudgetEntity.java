package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.dto.BudgetDTO;
import org.acme.dto.BudgetUpdateDTO;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_budget")
public class BudgetEntity extends PanacheEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    private Long productId;

    public BudgetEntity(BudgetDTO dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.productId = dto.getProductId();
    }

    public void update(BudgetUpdateDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.phone() != null) {
            this.phone = dto.phone();
        }
    }


}
