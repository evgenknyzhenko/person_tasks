package com.example.person_tasks.service.impl;

import com.example.person_tasks.dto.CompanyDto;
import com.example.person_tasks.dto.CompanyUpsertRequest;
import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.entity.Company;
import com.example.person_tasks.enums.CompanyPosition;
import com.example.person_tasks.enums.ParticipationType;
import com.example.person_tasks.repository.CompanyRepository;
import com.example.person_tasks.repository.TaskRepository;
import com.example.person_tasks.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final TaskRepository taskRepository;

    @Override
    public Page<CompanyDto> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(CompanyDto::toDto);
    }

    @Override
    public CompanyDto findById(UUID id) {
        return CompanyDto.toDto(getById(id));
    }

    @Override
    public Page<TaskDto> getCompanyTasks(UUID companyId, CompanyPosition position, ParticipationType participationType, Pageable pageable) {
        getById(companyId);
        return taskRepository.findDistinctPageByCompanyIdAndFilters(companyId, position, participationType, pageable)
                .map(TaskDto::toDto);
    }

    @Override
    @Transactional
    public CompanyDto create(CompanyUpsertRequest request) {
        Company company = new Company();
        company.setName(request.name());
        return CompanyDto.toDto(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyDto update(UUID id, CompanyUpsertRequest request) {
        Company company = getById(id);
        company.setName(request.name());
        return CompanyDto.toDto(companyRepository.save(company));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!companyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        companyRepository.deleteById(id);
    }

    @Override
    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
    }
}
