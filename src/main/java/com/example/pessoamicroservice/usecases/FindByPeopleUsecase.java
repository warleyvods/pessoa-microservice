package com.example.pessoamicroservice.usecases;

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
public class FindByPeopleUsecase {

    private final PeopleService peopleService;
    private final AddressClient addressClient;

    public PeopleResponseDTO response(Long id) {
        final People people = peopleService.findById(id);
        final List<AddressResponseDTO> addressFromClient = getAddressResponseDTOS(id);

        return new PeopleResponseDTO(id, people.getName(), people.getCpf(), people.getIdade(), addressFromClient);
    }

    public List<AddressResponseDTO> getAddressResponseDTOS(Long id) {
        return addressClient.getAddressFromClient(id);
    }
}
