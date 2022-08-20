package com.example.pessoamicroservice.gateways;

import com.example.pessoamicroservice.exceptions.PeopleNotFoundException;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.repository.PeopleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleGatewayUnitTest {

    @InjectMocks
    private PeopleGateway peopleGateway;

    @Mock
    private PeopleRepository peopleRepository;

    @Test
    void shouldSavePeopleWhitoutError() {
        People people = new People();
        people.setCpf("03964879126");
        people.setAge(19);
        people.setName("Warley");

        when(peopleRepository.save(people)).thenReturn(people);

        final People response = peopleGateway.save(people);

        assertEquals(people.getCpf(), response.getCpf());
        assertEquals(people.getId(), response.getId());
        assertEquals(people.getName(), response.getName());

        verify(peopleRepository).save(people);
    }

    @Test
    void shouldDeleteById() {
        doNothing().when(peopleRepository).deleteById(anyLong());

        peopleGateway.deleteById(anyLong());

        verify(peopleRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void shouldFindById() {
        People people = new People();
        people.setCpf("03964879126");
        people.setAge(19);
        people.setName("Warley");

        when(peopleRepository.findById(anyLong())).thenReturn(Optional.of(people));

        final People response = peopleGateway.findById(anyLong());

        assertEquals(people.getCpf(), response.getCpf());
        assertEquals(people.getId(), response.getId());
        assertEquals(people.getName(), response.getName());
    }

    @Test
    void shouldFindAll() {
        People people0 = new People();
        people0.setCpf("123456789");
        people0.setAge(19);
        people0.setName("Warley");

        People people1 = new People();
        people1.setCpf("123456789");
        people1.setAge(19);
        people1.setName("Warley");

        when(peopleRepository.findAll()).thenReturn(List.of(people0, people1));

        final List<People> listPeople = peopleGateway.findAll();

        assertEquals(2, listPeople.size());

        assertEquals(people0.getCpf(), listPeople.get(0).getCpf());
        assertEquals(people0.getAge(), listPeople.get(0).getAge());
        assertEquals(people0.getName(), listPeople.get(0).getName());

        assertEquals(people1.getCpf(), listPeople.get(1).getCpf());
        assertEquals(people1.getAge(), listPeople.get(1).getAge());
        assertEquals(people1.getName(), listPeople.get(1).getName());
    }
}
