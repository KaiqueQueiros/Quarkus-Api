package org.acme.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.acme.entity.BudgetEntity;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Jacksonized
public class BudgetDTO {
    @NotEmpty(message = "Informe o nome")
    private String name;
    @NotEmpty(message = "Informe o email")
    private String email;
    @NotEmpty(message = "Informe o telefone")
    private String phone;
    private Long productId;

    public BudgetDTO(BudgetEntity budgetEntity) {
        this.name = budgetEntity.getName();
        this.email = budgetEntity.getName();
        this.phone = budgetEntity.getPhone();
        this.productId = budgetEntity.getProductId();
    }
}
