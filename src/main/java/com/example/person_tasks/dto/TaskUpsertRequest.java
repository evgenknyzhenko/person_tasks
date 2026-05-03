package com.example.person_tasks.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskUpsertRequest(
        @NotBlank String title
) {
}
