package com.example.pessoamicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleRequestDTO {

    private String name;
    private String cpf;
    private Integer idade;

}
