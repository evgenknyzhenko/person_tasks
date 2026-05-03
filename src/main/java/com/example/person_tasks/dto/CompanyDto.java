package com.example.person_tasks.dto;

import com.example.person_tasks.entity.Company;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CompanyDto(
        UUID id,
        String name,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        long version
) {
    public static CompanyDto toDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getCreatedAt(),
                company.getUpdatedAt(),
                company.getVersion()
        );
    }
}
