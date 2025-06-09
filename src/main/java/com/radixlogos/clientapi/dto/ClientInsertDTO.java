package com.radixlogos.clientapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientInsertDTO(
        @NotBlank(message = "Campo não pode ser vazio")
        String name,
        String cpf,
        Double income,
        @PastOrPresent(message = "A data não pode ser futura")
        LocalDate birthDate,
        Integer children
) {
}
