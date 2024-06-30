package com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos;

import com.seek.pruebatecnica.domain.entities.Candidate;

import java.time.LocalDateTime;

public record CandidateResponse(
        Long id,
        String name,
        String lastName,
        String email,
        int age,
        int salaryExpected,
        String gender,
        LocalDateTime createAt
) {

    public static CandidateResponse convertFromEntity(Candidate candidate) {
        return new CandidateResponse(candidate.getId(),
                candidate.getName(),
                candidate.getLastName(),
                candidate.getEmail(),
                candidate.getAge(),
                candidate.getSalaryExpected(),
                candidate.getGender(),
                candidate.getCreateAt()
        );
    }
}
