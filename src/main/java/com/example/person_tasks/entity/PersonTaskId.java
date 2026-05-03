package com.example.person_tasks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonTaskId implements Serializable {

    @Column(name = "person_id")
    private UUID personId;

    @Column(name = "task_id")
    private UUID taskId;
}
