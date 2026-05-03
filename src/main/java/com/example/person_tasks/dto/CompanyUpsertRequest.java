package com.example.person_tasks.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyUpsertRequest(
        @NotBlank String name
) {
}
