package com.example.pessoamicroservice.service;

import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository repository;

    public PeopleService(PeopleRepository repository) {
        this.repository = repository;
    }

    public People save(People people) {
        return repository.save(people);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public People findById(Long id) {
        return repository.findById(id).orElseThrow(NullPointerException::new);
    }

    public List<People> findAll() {
        return repository.findAll();
    }

}
