package com.example.pessoamicroservice.usecases;

import com.example.pessoamicroservice.gateway.KafkaProducerGateway;
import com.example.pessoamicroservice.gateway.PeopleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePeopleUsecase {

    private final PeopleGateway peopleGateway;
    private final KafkaProducerGateway kafkaProducerGateway;
    private final FindByPeopleUsecase peopleUsecase;

    public void deleteById(Long id) {
        if (verifyIfExistAddress(id)) {
            peopleGateway.deleteById(id);
            kafkaProducerGateway.send(String.valueOf(id));
        }
    }

    private boolean verifyIfExistAddress(Long id) {
        return peopleUsecase.response(id).hasAddress();
    }
}
