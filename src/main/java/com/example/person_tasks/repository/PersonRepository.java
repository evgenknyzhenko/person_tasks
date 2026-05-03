package com.example.person_tasks.repository;

import com.example.person_tasks.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query("SELECT p FROM Person p LEFT JOIN p.personTasks pt GROUP BY p ORDER BY COUNT(pt) DESC")
    List<Person> findPersonsOrderByTaskCountDesc(Pageable pageable);
}
