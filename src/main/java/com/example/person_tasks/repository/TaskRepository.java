package com.example.person_tasks.repository;

import com.example.person_tasks.entity.Task;
import com.example.person_tasks.enums.CompanyPosition;
import com.example.person_tasks.enums.ParticipationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("""
            SELECT t FROM Task t
            LEFT JOIN PersonTask pt ON pt.task = t AND (:participationType IS NULL OR pt.participationType = :participationType)
            GROUP BY t
            ORDER BY COUNT(pt) DESC
            """)
    List<Task> findTasksOrderByPersonCountDesc(
            @Param("participationType") ParticipationType participationType,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT DISTINCT t FROM Task t
                    JOIN PersonTask pt ON pt.task = t
                    JOIN CompanyPerson cp ON cp.person = pt.person
                    WHERE cp.company.id = :companyId
                    AND (:position IS NULL OR cp.position = :position)
                    AND (:participationType IS NULL OR pt.participationType = :participationType)
                    """,
            countQuery = """
                    SELECT COUNT(DISTINCT t.id) FROM Task t
                    JOIN PersonTask pt ON pt.task = t
                    JOIN CompanyPerson cp ON cp.person = pt.person
                    WHERE cp.company.id = :companyId
                    AND (:position IS NULL OR cp.position = :position)
                    AND (:participationType IS NULL OR pt.participationType = :participationType)
                    """
    )
    Page<Task> findDistinctPageByCompanyIdAndFilters(
            @Param("companyId") UUID companyId,
            @Param("position") CompanyPosition position,
            @Param("participationType") ParticipationType participationType,
            Pageable pageable
    );
}
