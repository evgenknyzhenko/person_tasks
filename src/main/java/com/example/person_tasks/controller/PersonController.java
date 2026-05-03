package com.example.person_tasks.controller;

import com.example.person_tasks.dto.PersonAddCompanyRequest;
import com.example.person_tasks.dto.PersonDto;
import com.example.person_tasks.dto.PersonUpsertRequest;
import com.example.person_tasks.dto.PersonAddTaskRequest;
import com.example.person_tasks.enums.ParticipationType;
import com.example.person_tasks.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<PersonDto> list() {
        return personService.list();
    }

    @GetMapping("/{id}")
    public PersonDto get(@PathVariable UUID id) {
        return personService.get(id);
    }

    @GetMapping("/top-by-tasks")
    public PersonDto getTopByLinkedTasks(@RequestParam(required = false) ParticipationType participationType) {
        return personService.getTopByLinkedTasks(participationType);
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

    @PostMapping("/{personId}/companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCompanyLink(
            @PathVariable UUID personId,
            @PathVariable UUID companyId,
            @Valid @RequestBody PersonAddCompanyRequest request
    ) {
        personService.addCompanyLink(personId, companyId, request.position());
    }

    @DeleteMapping("/{personId}/companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCompanyLink(
            @PathVariable UUID personId,
            @PathVariable UUID companyId
    ) {
        personService.removeCompanyLink(personId, companyId);
    }

    @PostMapping("/{personId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addTaskLink(
            @PathVariable UUID personId,
            @PathVariable UUID taskId,
            @Valid @RequestBody PersonAddTaskRequest request
    ) {
        personService.addTaskLink(personId, taskId, request.participationType());
    }

    @DeleteMapping("/{personId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTaskLink(
            @PathVariable UUID personId,
            @PathVariable UUID taskId
    ) {
        personService.removeTaskLink(personId, taskId);
    }
}
