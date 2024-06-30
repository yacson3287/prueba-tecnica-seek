package com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos;

import com.seek.pruebatecnica.domain.entities.Candidate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UpdateCandidateRequest(
        @NotNull(message = "the field id is obligatory")
        Long id,
        @NotNull(message = "the field name is obligatory")
        String name,
        @NotNull(message = "the field lastName is obligatory")
        String lastName,
        @NotNull(message = "the field email is obligatory")
        @Email(message = "The field email has a format invalid")
        int age,
        int salaryExpected,
        String gender

) {

    public Candidate convertToEntity() {
        return Candidate
                .builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .age(age)
                .salaryExpected(salaryExpected)
                .gender(gender)
                .build();
    }
}
