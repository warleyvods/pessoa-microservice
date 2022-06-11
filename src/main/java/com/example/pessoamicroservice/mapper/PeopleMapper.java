package com.example.pessoamicroservice.mapper;

import com.example.pessoamicroservice.dtos.PeopleRequestDTO;
import com.example.pessoamicroservice.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.models.People;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeopleMapper extends EntityMapper<People, PeopleRequestDTO, PeopleResponseDTO> {
}
