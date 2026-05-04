package com.example.person_tasks.controller;

import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.dto.TaskUpsertRequest;
import com.example.person_tasks.enums.ParticipationType;
import com.example.person_tasks.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public Page<TaskDto> findAll(Pageable pageable) {
        return taskService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TaskDto findById(@PathVariable UUID id) {
        return taskService.findById(id);
    }

    @GetMapping("/top-by-persons")
    public TaskDto getTopByPersons(@RequestParam(required = false) ParticipationType participationType) {
        return taskService.getTopByLinkedPersons(participationType);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@Valid @RequestBody TaskUpsertRequest request) {
        return taskService.create(request);
    }

    @PutMapping("/{id}")
    public TaskDto update(@PathVariable UUID id, @Valid @RequestBody TaskUpsertRequest request) {
        return taskService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        taskService.delete(id);
    }
}
