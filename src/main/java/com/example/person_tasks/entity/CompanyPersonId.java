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
public class CompanyPersonId implements Serializable {

    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "person_id")
    private UUID personId;
}
