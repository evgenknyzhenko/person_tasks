package com.example.person_tasks.service.impl;

import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.dto.TaskUpsertRequest;
import com.example.person_tasks.entity.Task;
import com.example.person_tasks.enums.ParticipationType;
import com.example.person_tasks.repository.TaskRepository;
import com.example.person_tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(TaskDto::toDto)
                .toList();
    }

    @Override
    public TaskDto findById(UUID id) {
        return TaskDto.toDto(getById(id));
    }

    @Override
    public TaskDto getTopByLinkedPersons(ParticipationType participationType) {
        return taskRepository.findTasksOrderByPersonCountDesc(participationType, PageRequest.of(0, 1)).stream()
                .findFirst()
                .map(TaskDto::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    @Override
    @Transactional
    public TaskDto create(TaskUpsertRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        return TaskDto.toDto(taskRepository.save(task));
    }

    @Override
    @Transactional
    public TaskDto update(UUID id, TaskUpsertRequest request) {
        Task task = getById(id);
        task.setTitle(request.title());
        return TaskDto.toDto(taskRepository.save(task));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task getById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }
}
