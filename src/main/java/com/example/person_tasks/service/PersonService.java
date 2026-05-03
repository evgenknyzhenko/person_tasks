package com.example.person_tasks.service;

import com.example.person_tasks.dto.PersonDto;
import com.example.person_tasks.dto.PersonUpsertRequest;
import com.example.person_tasks.entity.Person;
import com.example.person_tasks.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDto> list() {
        return personRepository.findAll().stream()
                .map(PersonDto::toDto)
                .toList();
    }

    public PersonDto get(UUID id) {
        return PersonDto.toDto(getEntity(id));
    }

    @Transactional
    public PersonDto create(PersonUpsertRequest request) {
        Person person = new Person();
        person.setFullName(request.fullName());
        return PersonDto.toDto(personRepository.save(person));
    }

    @Transactional
    public PersonDto update(UUID id, PersonUpsertRequest request) {
        Person person = getEntity(id);
        person.setFullName(request.fullName());
        return PersonDto.toDto(personRepository.save(person));
    }

    @Transactional
    public void delete(UUID id) {
        if (!personRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personRepository.deleteById(id);
    }

    private Person getEntity(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }
}
