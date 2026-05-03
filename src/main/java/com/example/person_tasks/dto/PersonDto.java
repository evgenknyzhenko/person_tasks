package com.example.person_tasks.dto;

import com.example.person_tasks.entity.Person;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PersonDto(
        UUID id,
        String fullName,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        long version
) {
    public static PersonDto toDto(Person person) {
        return new PersonDto(
                person.getId(),
                person.getFullName(),
                person.getCreatedAt(),
                person.getUpdatedAt(),
                person.getVersion()
        );
    }
}
