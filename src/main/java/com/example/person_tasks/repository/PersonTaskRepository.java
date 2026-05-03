package com.example.person_tasks.repository;

import com.example.person_tasks.entity.PersonTask;
import com.example.person_tasks.entity.PersonTaskId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonTaskRepository extends JpaRepository<PersonTask, PersonTaskId> {
}
