package com.example.pessoamicroservice.gateway;

import com.example.pessoamicroservice.exceptions.PeopleNotFoundException;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeopleGateway {

    private final PeopleRepository repository;

    public People save(People people) {
        return repository.save(people);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public People findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PeopleNotFoundException("People not found!"));
    }

    public List<People> findAll() {
        return repository.findAll();
    }
}
