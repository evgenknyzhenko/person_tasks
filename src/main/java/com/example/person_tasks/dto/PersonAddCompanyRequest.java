package com.example.person_tasks.dto;

import com.example.person_tasks.enums.CompanyPosition;
import jakarta.validation.constraints.NotNull;

public record PersonAddCompanyRequest(
        @NotNull CompanyPosition position
) {
}
