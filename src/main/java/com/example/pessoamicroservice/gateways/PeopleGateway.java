package com.example.pessoamicroservice.gateways;

import com.example.pessoamicroservice.exceptions.PeopleNotFoundException;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record PeopleGateway(PeopleRepository repository) {

    public People save(final People people) {
        return repository.save(people);
    }

    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

    public People findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new PeopleNotFoundException("People not found!"));
    }

    public List<People> findAll() {
        return repository.findAll();
    }

    public People update(final People people) {
        return save(people);
    }

}
