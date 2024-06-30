package com.seek.pruebatecnica.infrastructure.db.jpa.model;

import com.seek.pruebatecnica.domain.entities.Candidate;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Hidden
@Table(name = "candidates")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CandidateJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private int age;
    private int salaryExpected;
    private String gender;
    private LocalDateTime createAt;

    @PrePersist
    private void prePersist(){
        createAt = LocalDateTime.now();
    }

    public Candidate convertToEntity() {
        return Candidate
                .builder()
                .id(id)
                .age(age)
                .email(email)
                .gender(gender)
                .lastName(lastName)
                .name(name)
                .salaryExpected(salaryExpected)
                .createAt(createAt)
                .build();
    }

    public static CandidateJPA convertFromEntity(Candidate candidate) {
        return CandidateJPA.builder()
                .id(candidate.getId())
                .age(candidate.getAge())
                .name(candidate.getName())
                .lastName(candidate.getLastName())
                .email(candidate.getEmail())
                .gender(candidate.getGender())
                .salaryExpected(candidate.getSalaryExpected())
                .createAt(candidate.getCreateAt())
                .build();
    }

}
