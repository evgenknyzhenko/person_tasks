package com.example.person_tasks.repository;

import com.example.person_tasks.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("select t from Task t left join PersonTask pt on pt.task = t group by t order by count(pt) desc")
    List<Task> findTasksOrderByPersonCountDesc(Pageable pageable);
}
