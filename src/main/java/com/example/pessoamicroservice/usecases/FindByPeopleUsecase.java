package com.example.pessoamicroservice.usecases;

import com.example.pessoamicroservice.client.AddressClient;
import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.gateways.PeopleGateway;
import com.example.pessoamicroservice.models.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public record FindByPeopleUsecase(PeopleGateway peopleGateway,
                                  AddressClient addressClient) {

    public PeopleResponseDTO response(Long id) {
        final People people = peopleGateway.findById(id);
        final List<AddressResponseDTO> addressFromClient = getAddressResponseDTOS(id);

        return new PeopleResponseDTO(id, people.getName(), people.getCpf(), people.getIdade(), addressFromClient);
    }

    public List<AddressResponseDTO> getAddressResponseDTOS(Long id) {
        log.info("Fetching address...");
        return addressClient.getAddressFromClient(id);
    }
}
