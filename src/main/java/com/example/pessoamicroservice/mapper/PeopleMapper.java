package com.example.pessoamicroservice.mapper;

import com.example.pessoamicroservice.controller.dtos.PeoplePutRequestDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleRequestDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.models.People;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PeopleMapper extends EntityMapper<People, PeopleRequestDTO, PeopleResponseDTO> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    People updatePeople(PeoplePutRequestDTO peoplePutRequestDTO, @MappingTarget People people);

}
