package com.example.person_tasks.service;

import com.example.person_tasks.dto.PersonDto;
import com.example.person_tasks.dto.PersonUpsertRequest;
import com.example.person_tasks.entity.Company;
import com.example.person_tasks.entity.CompanyPerson;
import com.example.person_tasks.entity.CompanyPersonId;
import com.example.person_tasks.entity.Person;
import com.example.person_tasks.entity.PersonTask;
import com.example.person_tasks.entity.PersonTaskId;
import com.example.person_tasks.entity.Task;
import com.example.person_tasks.enums.CompanyPosition;
import com.example.person_tasks.enums.ParticipationType;
import com.example.person_tasks.repository.PersonCompanyRepository;
import com.example.person_tasks.repository.PersonRepository;
import com.example.person_tasks.repository.PersonTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final TaskService taskService;
    private final CompanyService companyService;
    private final PersonTaskRepository personTaskRepository;
    private final PersonCompanyRepository personCompanyRepository;

    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(PersonDto::toDto)
                .toList();
    }

    public PersonDto findById(UUID id) {
        return PersonDto.toDto(getById(id));
    }

    public PersonDto getTopByLinkedTasks(ParticipationType participationType) {
        return personRepository.findPersonsOrderByTaskCountDesc(participationType, PageRequest.of(0, 1)).stream()
                .findFirst()
                .map(PersonDto::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }

    @Transactional
    public PersonDto create(PersonUpsertRequest request) {
        Person person = new Person();
        person.setFullName(request.fullName());
        return PersonDto.toDto(personRepository.save(person));
    }

    @Transactional
    public PersonDto update(UUID id, PersonUpsertRequest request) {
        Person person = getById(id);
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

    @Transactional
    public void addTaskLink(UUID personId, UUID taskId, ParticipationType participationType) {
        Person person = getById(personId);
        Task task = taskService.getById(taskId);

        PersonTaskId id = new PersonTaskId(personId, taskId);
        if (personTaskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Task already added to person");
        }

        PersonTask personTask = new PersonTask();
        personTask.setId(id);
        personTask.setPerson(person);
        personTask.setTask(task);
        personTask.setParticipationType(participationType);
        personTaskRepository.save(personTask);
    }

    @Transactional
    public void removeTaskLink(UUID personId, UUID taskId) {
        PersonTaskId id = new PersonTaskId(personId, taskId);
        if (!personTaskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found or not linked to person");
        }
        personTaskRepository.deleteById(id);
    }

    @Transactional
    public void addCompanyLink(UUID personId, UUID companyId, CompanyPosition position) {
        Person person = getById(personId);
        Company company = companyService.getById(companyId);

        CompanyPersonId id = new CompanyPersonId(companyId, personId);
        if (personCompanyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Company already added to person");
        }

        CompanyPerson companyPerson = new CompanyPerson();
        companyPerson.setId(id);
        companyPerson.setCompany(company);
        companyPerson.setPerson(person);
        companyPerson.setPosition(position);
        personCompanyRepository.save(companyPerson);
    }

    @Transactional
    public void removeCompanyLink(UUID personId, UUID companyId) {
        CompanyPersonId id = new CompanyPersonId(companyId, personId);
        if (!personCompanyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found or not linked to person");
        }
        personCompanyRepository.deleteById(id);
    }

    public Person getById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }
}
