package com.example.pessoamicroservice.controller;

import com.example.pessoamicroservice.controller.dtos.PeoplePutRequestDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleRequestDTO;
import com.example.pessoamicroservice.controller.dtos.PeopleResponseDTO;
import com.example.pessoamicroservice.gateways.PeopleGateway;
import com.example.pessoamicroservice.mapper.PeopleMapper;
import com.example.pessoamicroservice.models.People;
import com.example.pessoamicroservice.usecases.DeletePeopleUsecase;
import com.example.pessoamicroservice.usecases.FindByPeopleUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/people")
@RestController
public class PeopleController {

    private final PeopleGateway peopleGateway;
    private final PeopleMapper peopleMapper;
    private final FindByPeopleUsecase findByPeopleUsecase;
    private final DeletePeopleUsecase deletePeopleUsecase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PeopleResponseDTO save(@RequestBody @Valid final PeopleRequestDTO peopleRequestDTO) {
        final People people = peopleGateway.save(peopleMapper.toDomain(peopleRequestDTO));
        return peopleMapper.fromDomain(people);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public PeopleResponseDTO save(@RequestBody @Valid final PeoplePutRequestDTO peoplePutRequestDTO) {
        final People toUpdate = peopleGateway.findById(peoplePutRequestDTO.id());
        final People people = peopleMapper.updatePeople(peoplePutRequestDTO, toUpdate);
        return peopleMapper.fromDomain(peopleGateway.update(people));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public PeopleResponseDTO findById(@PathVariable final Long id) {
        return findByPeopleUsecase.response(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id) {
        deletePeopleUsecase.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PeopleResponseDTO> listAll() {
        return peopleMapper.fromDomainList(peopleGateway.findAll());
    }
}
