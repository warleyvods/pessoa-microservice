package com.example.pessoamicroservice.controller;

import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.exceptions.handler.ExceptionFilters;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PeopleControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PeopleRepository peopleRepository;

    @BeforeEach
    void setUp() {
        peopleRepository.deleteAll();
    }

    @Test
    void shouldSavePeople() {
        People people = new People();
        people.setCpf("02312341654");
        people.setIdade(10);
        people.setName("Warley");

        final HttpEntity<People> request = new HttpEntity<>(people);

        final ResponseEntity<PeopleResponseDTO> response = testRestTemplate.exchange("/api/people", HttpMethod.POST, request, PeopleResponseDTO.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(people.getCpf(), Objects.requireNonNull(response.getBody()).cpf());
        assertEquals(people.getIdade(), response.getBody().idade());
        assertEquals(people.getName(), response.getBody().name());
        assertNull(response.getBody().address());
    }

    private static Stream<Arguments> providers() {
        return Stream.of(
                Arguments.of(null, null, null),
                Arguments.of("", "", null),
                Arguments.of(null, null, 10),
                Arguments.of("", null, 10),
                Arguments.of("", "", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("providers")
    void shouldNotSavePeopleWhenPeopleNameAreNull(String name, String cpf, Integer age) {
        People people = new People();
        people.setName(name);
        people.setCpf(cpf);
        people.setIdade(age);

        final HttpEntity<People> request = new HttpEntity<>(people);

        final var response = testRestTemplate.exchange("/api/people", HttpMethod.POST, request, ExceptionFilters.class);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Argument not valid", Objects.requireNonNull(response.getBody()).title());
    }

    @Test
    void shouldFindByIdWithOneAddress() {
        People people = new People();
        people.setCpf("02312341654");
        people.setIdade(10);
        people.setName("Warley");
        final Long peopleSavedId = peopleRepository.save(people).getId();

        final ResponseEntity<PeopleResponseDTO> response = testRestTemplate.exchange("/api/people/" + peopleSavedId, HttpMethod.GET, null, PeopleResponseDTO.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(people.getCpf(), Objects.requireNonNull(response.getBody()).cpf());
        assertEquals(people.getIdade(), response.getBody().idade());
        assertEquals(people.getName(), response.getBody().name());
        assertEquals(0, response.getBody().address().size());
    }

    @Test
    void shouldFindByIdWithFallback() {
        People people = new People();
        people.setId(2L);
        people.setCpf("02312341654");
        people.setIdade(10);
        people.setName("Warley");
        final Long peopleSavedId = peopleRepository.save(people).getId();

        final ResponseEntity<PeopleResponseDTO> response = testRestTemplate.exchange("/api/people/" + peopleSavedId, HttpMethod.GET, null, PeopleResponseDTO.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(people.getCpf(), Objects.requireNonNull(response.getBody()).cpf());
        assertEquals(people.getIdade(), response.getBody().idade());
        assertEquals(people.getName(), response.getBody().name());
        assertEquals(0, response.getBody().address().size());
    }

    @Test
    void shouldFindByIdWIREMOCK() {
        People people = new People();
        people.setCpf("02312341654");
        people.setIdade(10);
        people.setName("Warley");
        final Long peopleSavedId = peopleRepository.save(people).getId();

        final ResponseEntity<PeopleResponseDTO> response = testRestTemplate.exchange("/api/people/" + peopleSavedId, HttpMethod.GET, null, PeopleResponseDTO.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(people.getCpf(), Objects.requireNonNull(response.getBody()).cpf());
        assertEquals(people.getIdade(), response.getBody().idade());
        assertEquals(people.getName(), response.getBody().name());
        assertEquals(0, response.getBody().address().size());
    }

    @Test
    void shouldDeletePeopleById() {
        People people = new People();
        people.setCpf("02312341654");
        people.setIdade(10);
        people.setName("Warley");
        final Long peopleSavedId = peopleRepository.save(people).getId();

        final ResponseEntity<Void> response = testRestTemplate.exchange("/api/people/" + peopleSavedId, HttpMethod.DELETE, null, Void.class);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void shouldListAllPeople() {
        People people0 = new People();
        people0.setCpf("123456789");
        people0.setIdade(19);
        people0.setName("Warley");

        People people1 = new People();
        people1.setCpf("123456789");
        people1.setIdade(19);
        people1.setName("Warley");
        peopleRepository.saveAll(List.of(people0, people1));

        final var listPeople = testRestTemplate.exchange("/api/people", HttpMethod.GET, null, new ParameterizedTypeReference<List<PeopleResponseDTO>>() {});

        assertEquals(2, Objects.requireNonNull(listPeople.getBody()).size());

        assertEquals(people0.getCpf(), listPeople.getBody().get(0).cpf());
        assertEquals(people0.getIdade(), listPeople.getBody().get(0).idade());
        assertEquals(people0.getName(), listPeople.getBody().get(0).name());

        assertEquals(people0.getCpf(), listPeople.getBody().get(1).cpf());
        assertEquals(people0.getIdade(), listPeople.getBody().get(1).idade());
        assertEquals(people0.getName(), listPeople.getBody().get(1).name());
    }
}
