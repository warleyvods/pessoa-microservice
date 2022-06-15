package com.example.pessoamicroservice.controller.mapper;

import com.example.pessoamicroservice.controller.dtos.PeopleRequestDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.models.People;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeopleMapper extends EntityMapper<People, PeopleRequestDTO, PeopleResponseDTO> {
}
