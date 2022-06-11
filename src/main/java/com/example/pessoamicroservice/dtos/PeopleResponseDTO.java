package com.example.pessoamicroservice.dtos;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PeopleResponseDTO {

    private Long id;
    private String name;
    private String cpf;
    private Integer idade;
    private List<AddressResponseDTO> address;

}
