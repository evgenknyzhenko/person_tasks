package com.example.person_tasks.controller;

import com.example.person_tasks.dto.CompanyDto;
import com.example.person_tasks.dto.CompanyUpsertRequest;
import com.example.person_tasks.dto.TaskDto;
import com.example.person_tasks.enums.CompanyPosition;
import com.example.person_tasks.enums.ParticipationType;
import com.example.person_tasks.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDto findById(@PathVariable UUID id) {
        return companyService.findById(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getCompanyTasks(
            @PathVariable UUID id,
            @RequestParam(required = false) CompanyPosition position,
            @RequestParam(required = false) ParticipationType participationType
    ) {
        return companyService.getCompanyTasks(id, position, participationType);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto create(@Valid @RequestBody CompanyUpsertRequest request) {
        return companyService.create(request);
    }

    @PutMapping("/{id}")
    public CompanyDto update(@PathVariable UUID id, @Valid @RequestBody CompanyUpsertRequest request) {
        return companyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        companyService.delete(id);
    }
}
