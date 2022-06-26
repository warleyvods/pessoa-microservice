package com.example.pessoamicroservice.gateways;

import com.example.pessoamicroservice.exceptions.PeopleNotFoundException;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PeopleGatewayIntegrationTest {

    @Autowired
    private PeopleGateway peopleGateway;

    @Autowired
    private PeopleRepository peopleRepository;

    @BeforeEach
    void setUp() {
        peopleRepository.deleteAll();
    }

    @Test
    void shouldSavePeopleWhitoutError() {
        People people = new People();
        people.setCpf("03964879126");
        people.setIdade(19);
        people.setName("Warley");

        final People response = peopleGateway.save(people);

        assertEquals(people.getCpf(), response.getCpf());
        assertEquals(people.getId(), response.getId());
        assertEquals(people.getName(), response.getName());
    }

    @Test
    void shouldDeleteById() {
        People people = new People();
        people.setCpf("03964879126");
        people.setIdade(19);
        people.setName("Warley");

        final People response = peopleGateway.save(people);
        peopleGateway.deleteById(response.getId());

        assertThrows(PeopleNotFoundException.class, () -> peopleGateway.findById(response.getId()));
    }

    @Test
    void shouldFindById() {
        People people = new People();
        people.setCpf("123456789");
        people.setIdade(19);
        people.setName("Warley");

        final People savedPeople = peopleGateway.save(people);
        final People response = peopleGateway.findById(savedPeople.getId());

        assertEquals("123456789", response.getCpf());
        assertEquals(people.getId(), response.getId());
        assertEquals(people.getName(), response.getName());
    }

    @Test
    void shouldNotFindById() {
        assertThrows(PeopleNotFoundException.class, () -> peopleGateway.findById(99L));
    }

    @Test
    void shouldFindAll() {
        People people0 = new People();
        people0.setCpf("123456789");
        people0.setIdade(19);
        people0.setName("Warley");

        People people1 = new People();
        people1.setCpf("123456789");
        people1.setIdade(19);
        people1.setName("Warley");
        peopleRepository.saveAll(List.of(people0, people1));

        final List<People> listPeople = peopleGateway.findAll();

        assertEquals(2, listPeople.size());

        assertEquals(people0.getCpf(), listPeople.get(0).getCpf());
        assertEquals(people0.getIdade(), listPeople.get(0).getIdade());
        assertEquals(people0.getName(), listPeople.get(0).getName());

        assertEquals(people1.getCpf(), listPeople.get(1).getCpf());
        assertEquals(people1.getIdade(), listPeople.get(1).getIdade());
        assertEquals(people1.getName(), listPeople.get(1).getName());
    }
}
