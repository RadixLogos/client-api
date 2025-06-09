package com.radixlogos.clientapi.dto;

import com.radixlogos.clientapi.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientDTO(
        Long id,
        String name,
        String cpf,
        Double income,
        LocalDate birthDate,
        Integer children) {
    public static ClientDTO fromClient(Client c){
        return new ClientDTO(
                c.getId(),
                c.getName(),
                c.getCpf(),
                c.getIncome(),
                c.getBirthDate(),
                c.getChildren());
    }
}

