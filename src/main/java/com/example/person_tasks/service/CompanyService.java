package com.example.person_tasks.service;

import com.example.person_tasks.dto.CompanyDto;
import com.example.person_tasks.dto.CompanyUpsertRequest;
import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.entity.Company;
import com.example.person_tasks.enums.CompanyPosition;
import com.example.person_tasks.enums.ParticipationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {

    /**
     * Returns all companies.
     *
     * @return list of companies as DTOs
     */
    Page<CompanyDto> findAll(Pageable pageable);

    /**
     * Finds a company by identifier.
     *
     * @param id company identifier
     * @return company DTO
     */
    CompanyDto findById(UUID id);

    /**
     * Returns tasks linked to a company with optional filters.
     *
     * @param companyId company identifier
     * @param position optional company position filter
     * @param participationType optional participation type filter
     * @return list of tasks as DTOs
     */
    Page<TaskDto> getCompanyTasks(UUID companyId, CompanyPosition position, ParticipationType participationType, Pageable pageable);

    /**
     * Creates a new company.
     *
     * @param request create/update payload
     * @return created company DTO
     */
    CompanyDto create(CompanyUpsertRequest request);

    /**
     * Updates an existing company.
     *
     * @param id company identifier
     * @param request update payload
     * @return updated company DTO
     */
    CompanyDto update(UUID id, CompanyUpsertRequest request);

    /**
     * Deletes a company by identifier.
     *
     * @param id company identifier
     */
    void delete(UUID id);

    /**
     * Returns a company entity by identifier.
     *
     * @param id company identifier
     * @return company entity
     */
    Company getById(UUID id);
}
