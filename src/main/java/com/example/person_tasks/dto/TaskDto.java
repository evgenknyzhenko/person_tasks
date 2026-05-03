package com.example.person_tasks.dto;

import com.example.person_tasks.entity.Task;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        long version
) {
    public static TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getVersion()
        );
    }
}
