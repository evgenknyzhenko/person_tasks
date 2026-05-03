package com.example.person_tasks.repository;

import com.example.person_tasks.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t LEFT JOIN PersonTask pt ON pt.task = t GROUP BY t ORDER BY COUNT(pt) DESC")
    List<Task> findTasksOrderByPersonCountDesc(Pageable pageable);

    @Query("SELECT DISTINCT t FROM Task t JOIN PersonTask pt ON pt.task = t JOIN CompanyPerson cp ON cp.person = pt.person WHERE cp.company.id = :companyId")
    List<Task> findDistinctByCompanyId(@Param("companyId") UUID companyId);
}
