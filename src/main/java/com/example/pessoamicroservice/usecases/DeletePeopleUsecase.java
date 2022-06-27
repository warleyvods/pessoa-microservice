package com.example.pessoamicroservice.usecases;

import com.example.pessoamicroservice.gateways.KafkaProducerGateway;
import com.example.pessoamicroservice.gateways.PeopleGateway;
import org.springframework.stereotype.Component;

@Component
public record DeletePeopleUsecase(
        PeopleGateway peopleGateway,
        KafkaProducerGateway kafkaProducerGateway,
        FindByPeopleUsecase peopleUsecase
) {

    public void deleteById(Long id) {
        if (verifyIfExistAddress(id)) {
            peopleGateway.deleteById(id);
            kafkaProducerGateway.send(String.valueOf(id));
        }
        peopleGateway.deleteById(id);
    }

    private boolean verifyIfExistAddress(Long id) {
        return peopleUsecase.response(id).hasAddress();
    }
}
