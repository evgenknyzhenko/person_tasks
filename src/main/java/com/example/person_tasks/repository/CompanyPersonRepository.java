package com.example.person_tasks.repository;

import com.example.person_tasks.entity.CompanyPerson;
import com.example.person_tasks.entity.CompanyPersonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyPersonRepository extends JpaRepository<CompanyPerson, CompanyPersonId> {
}
