package com.example.pessoamicroservice.controller.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PeopleRequestDTO(

        @NotBlank
        String name,

        @NotBlank
        String cpf,

        @NotNull
        Integer idade
) {
}
