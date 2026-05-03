package com.example.person_tasks.dto;

import com.example.person_tasks.enums.ParticipationType;
import jakarta.validation.constraints.NotNull;

public record PersonAddTaskRequest(
        @NotNull ParticipationType participationType
) {
}
