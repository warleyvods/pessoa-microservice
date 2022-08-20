package com.example.pessoamicroservice.controller.dtos;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public record PeopleResponseDTO(
        Long id,
        String name,
        String cpf,
        Integer age,
        @JsonInclude(NON_NULL)
        List<AddressResponseDTO> address
) {

    public boolean hasAddress() {
        return address != null && !address.isEmpty();
    }

}
