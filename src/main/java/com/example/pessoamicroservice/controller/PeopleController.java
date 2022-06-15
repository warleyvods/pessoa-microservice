package com.example.pessoamicroservice.controller;

import com.example.pessoamicroservice.factory.PeopleFactory;
import com.example.pessoamicroservice.controller.dtos.PeopleRequestDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.mapper.PeopleMapper;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/people")
@RestController
public class PeopleController {
    private final PeopleService peopleService;
    private final PeopleMapper peopleMapper;
    private final PeopleFactory peopleFactory;

    public PeopleController(PeopleService peopleService, PeopleMapper peopleMapper, PeopleFactory peopleFactory) {
        this.peopleService = peopleService;
        this.peopleMapper = peopleMapper;
        this.peopleFactory = peopleFactory;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PeopleResponseDTO save(@RequestBody @Valid PeopleRequestDTO peopleRequestDTO) {
        People people = peopleService.save(peopleMapper.toDomain(peopleRequestDTO));
        return peopleMapper.fromDomain(people);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public PeopleResponseDTO findById(@PathVariable Long id) {
        return peopleFactory.response(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        peopleService.deleteById(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PeopleResponseDTO> listAll() {
        return peopleMapper.fromDomainList(peopleService.findAll());
    }
}
