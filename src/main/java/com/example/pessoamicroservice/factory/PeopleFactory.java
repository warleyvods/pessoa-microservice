package com.example.pessoamicroservice.factory;

import com.example.pessoamicroservice.client.AddressClient;
import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public record PeopleFactory(PeopleService peopleService, AddressClient addressClient) {

    public PeopleResponseDTO response(Long id) {
        final People people = peopleService.findById(id);
        final List<AddressResponseDTO> addressFromClient = addressClient.getAddressFromClient(id);

        return new PeopleResponseDTO(id, people.getName(), people.getCpf(), people.getIdade(), addressFromClient);
    }
}
