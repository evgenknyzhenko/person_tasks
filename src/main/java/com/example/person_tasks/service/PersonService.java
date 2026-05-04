package com.example.person_tasks.service;

import com.example.person_tasks.dto.PersonDto;
import com.example.person_tasks.dto.PersonUpsertRequest;
import com.example.person_tasks.entity.Person;
import com.example.person_tasks.enums.CompanyPosition;
import com.example.person_tasks.enums.ParticipationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PersonService {

    /**
     * Returns all persons.
     *
     * @return list of persons as DTOs
     */
    Page<PersonDto> findAll(Pageable pageable);

    /**
     * Finds a person by identifier.
     *
     * @param id person identifier
     * @return person DTO
     */
    PersonDto findById(UUID id);

    /**
     * Returns the top person by number of linked tasks.
     *
     * @param participationType optional filter by participation type
     * @return person DTO
     */
    PersonDto getTopByLinkedTasks(ParticipationType participationType);

    /**
     * Creates a new person.
     *
     * @param request create/update payload
     * @return created person DTO
     */
    PersonDto create(PersonUpsertRequest request);

    /**
     * Updates an existing person.
     *
     * @param id person identifier
     * @param request update payload
     * @return updated person DTO
     */
    PersonDto update(UUID id, PersonUpsertRequest request);

    /**
     * Deletes a person by identifier.
     *
     * @param id person identifier
     */
    void delete(UUID id);

    /**
     * Creates a link between a person and a task.
     *
     * @param personId person identifier
     * @param taskId task identifier
     * @param participationType participation type for the link
     */
    void addTaskLink(UUID personId, UUID taskId, ParticipationType participationType);

    /**
     * Removes a link between a person and a task.
     *
     * @param personId person identifier
     * @param taskId task identifier
     */
    void removeTaskLink(UUID personId, UUID taskId);

    /**
     * Creates a link between a person and a company.
     *
     * @param personId person identifier
     * @param companyId company identifier
     * @param position person's position in company
     */
    void addCompanyLink(UUID personId, UUID companyId, CompanyPosition position);

    /**
     * Removes a link between a person and a company.
     *
     * @param personId person identifier
     * @param companyId company identifier
     */
    void removeCompanyLink(UUID personId, UUID companyId);

    /**
     * Returns a person entity by identifier.
     *
     * @param id person identifier
     * @return person entity
     */
    Person getById(UUID id);
}
