package com.example.person_tasks.service;

import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.dto.TaskUpsertRequest;
import com.example.person_tasks.entity.Task;
import com.example.person_tasks.enums.ParticipationType;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    /**
     * Returns all tasks.
     *
     * @return list of tasks as DTOs
     */
    List<TaskDto> findAll();

    /**
     * Finds a task by identifier.
     *
     * @param id task identifier
     * @return task DTO
     */
    TaskDto findById(UUID id);

    /**
     * Returns the top task by number of linked persons.
     *
     * @param participationType optional filter by participation type
     * @return task DTO
     */
    TaskDto getTopByLinkedPersons(ParticipationType participationType);

    /**
     * Creates a new task.
     *
     * @param request create/update payload
     * @return created task DTO
     */
    TaskDto create(TaskUpsertRequest request);

    /**
     * Updates an existing task.
     *
     * @param id task identifier
     * @param request update payload
     * @return updated task DTO
     */
    TaskDto update(UUID id, TaskUpsertRequest request);

    /**
     * Deletes a task by identifier.
     *
     * @param id task identifier
     */
    void delete(UUID id);

    /**
     * Returns a task entity by identifier.
     *
     * @param id task identifier
     * @return task entity
     */
    Task getById(UUID id);
}
