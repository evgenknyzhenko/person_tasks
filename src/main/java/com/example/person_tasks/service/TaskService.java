package com.example.person_tasks.service;

import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.dto.TaskUpsertRequest;
import com.example.person_tasks.entity.Task;
import com.example.person_tasks.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> list() {
        return taskRepository.findAll().stream()
                .map(TaskDto::toDto)
                .toList();
    }

    public TaskDto get(UUID id) {
        return TaskDto.toDto(getEntity(id));
    }

    @Transactional
    public TaskDto create(TaskUpsertRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        return TaskDto.toDto(taskRepository.save(task));
    }

    @Transactional
    public TaskDto update(UUID id, TaskUpsertRequest request) {
        Task task = getEntity(id);
        task.setTitle(request.title());
        return TaskDto.toDto(taskRepository.save(task));
    }

    @Transactional
    public void delete(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        taskRepository.deleteById(id);
    }

    private Task getEntity(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }
}
