package org.acme.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record BudgetUpdateDTO(
        @NotEmpty(message = "Informe o nome")
        String name,
        @Email(message = "Infome um email valido")
        @JsonProperty("email")
        String email,
        @NotEmpty(message = "Informe o telefone")
        String phone
) {
}
