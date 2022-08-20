package com.example.pessoamicroservice.controller.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PeoplePutRequestDTO(

        @NotNull(message = "id must be not null")
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String cpf,

        @NotNull
        Integer age
) {

}
