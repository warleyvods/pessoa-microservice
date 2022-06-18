package com.example.pessoamicroservice.usecases;

import com.example.pessoamicroservice.service.KafkaProducerService;
import com.example.pessoamicroservice.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePeopleUsecase {

    private final PeopleService peopleService;
    private final KafkaProducerService kafkaProducerService;
    private final FindByPeopleUsecase peopleUsecase;

    public void deleteById(Long id) {
        if (verifyIfExistAddress(id)) {
            peopleService.deleteById(id);
            kafkaProducerService.send(String.valueOf(id));
        }
    }

    private boolean verifyIfExistAddress(Long id) {
        return peopleUsecase.response(id).hasAddress();
    }
}
