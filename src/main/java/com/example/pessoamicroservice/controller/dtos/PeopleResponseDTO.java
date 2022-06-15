package com.example.pessoamicroservice.controller.dtos;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;

import java.util.List;

public record PeopleResponseDTO(
        Long id,
        String name,
        String cpf,
        Integer idade,
        List<AddressResponseDTO> address
) {

}
