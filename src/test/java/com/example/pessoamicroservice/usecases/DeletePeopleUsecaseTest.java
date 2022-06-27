package com.example.pessoamicroservice.usecases;

import com.example.pessoamicroservice.client.dto.AddressResponseDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.gateways.KafkaProducerGateway;
import com.example.pessoamicroservice.gateways.PeopleGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeletePeopleUsecaseTest {

    @InjectMocks
    private DeletePeopleUsecase deletePeopleUsecase;

    @Mock
    private FindByPeopleUsecase peopleUsecase;

    @Mock
    private PeopleGateway peopleGateway;

    @Mock
    private KafkaProducerGateway kafkaProducerGateway;

    @Test
    void shouldDeleteById() {
        final long id = 10;
        final var addressResponseDTO = new AddressResponseDTO(1L, "Rua X", "0", "500", "74565640", "Urias", "Goiania", "Brasil");
        final var peopleResponseDTO = new PeopleResponseDTO(1L, "Warley", "12345678911", 10, List.of(addressResponseDTO));

        doNothing().when(peopleGateway).deleteById(id);
        when(peopleUsecase.response(id)).thenReturn(peopleResponseDTO);

        deletePeopleUsecase.deleteById(id);

        verify(kafkaProducerGateway).send(String.valueOf(id));
    }
}
