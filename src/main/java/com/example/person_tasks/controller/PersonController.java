package com.example.person_tasks.controller;

import com.example.person_tasks.dto.PersonDto;
import com.example.person_tasks.dto.PersonUpsertRequest;
import com.example.person_tasks.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDto> list() {
        return personService.list();
    }

    @GetMapping("/{id}")
    public PersonDto get(@PathVariable UUID id) {
        return personService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@Valid @RequestBody PersonUpsertRequest request) {
        return personService.create(request);
    }

    @PutMapping("/{id}")
    public PersonDto update(@PathVariable UUID id, @Valid @RequestBody PersonUpsertRequest request) {
        return personService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        personService.delete(id);
    }
}
