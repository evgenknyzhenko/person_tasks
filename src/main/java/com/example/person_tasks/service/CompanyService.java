package com.example.person_tasks.service;

import com.example.person_tasks.dto.CompanyDto;
import com.example.person_tasks.dto.CompanyUpsertRequest;
import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.entity.Company;
import com.example.person_tasks.enums.CompanyPosition;
import com.example.person_tasks.enums.ParticipationType;
import com.example.person_tasks.repository.CompanyRepository;
import com.example.person_tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final TaskRepository taskRepository;

    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream()
                .map(CompanyDto::toDto)
                .toList();
    }

    public CompanyDto findById(UUID id) {
        return CompanyDto.toDto(getById(id));
    }

    public List<TaskDto> getCompanyTasks(UUID companyId, CompanyPosition position, ParticipationType participationType) {
        getById(companyId);
        return taskRepository.findDistinctByCompanyIdAndFilters(companyId, position, participationType).stream()
                .map(TaskDto::toDto)
                .toList();
    }

    @Transactional
    public CompanyDto create(CompanyUpsertRequest request) {
        Company company = new Company();
        company.setName(request.name());
        return CompanyDto.toDto(companyRepository.save(company));
    }

    @Transactional
    public CompanyDto update(UUID id, CompanyUpsertRequest request) {
        Company company = getById(id);
        company.setName(request.name());
        return CompanyDto.toDto(companyRepository.save(company));
    }

    @Transactional
    public void delete(UUID id) {
        if (!companyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        companyRepository.deleteById(id);
    }

    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
    }
}
