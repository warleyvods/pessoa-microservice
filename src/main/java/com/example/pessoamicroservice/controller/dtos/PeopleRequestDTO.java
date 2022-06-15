package com.example.pessoamicroservice.controller.dtos;

public record PeopleRequestDTO(
        String name,
        String cpf,
        Integer idade
) {
}
