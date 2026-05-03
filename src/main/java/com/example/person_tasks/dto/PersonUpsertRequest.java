package com.example.person_tasks.dto;

import jakarta.validation.constraints.NotBlank;

public record PersonUpsertRequest(
        @NotBlank String fullName
) {
}
