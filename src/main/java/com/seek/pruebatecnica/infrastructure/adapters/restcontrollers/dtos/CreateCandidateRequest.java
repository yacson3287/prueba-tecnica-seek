package com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos;

import com.seek.pruebatecnica.domain.entities.Candidate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateCandidateRequest(
        @NotNull(message = "the field Name is obligatory")
        String name,
        @NotNull(message = "the field LastName is obligatory")
        String lastName,
        @NotNull(message = "the field email is obligatory")
        @Email(message = "The field email has a format invalid")
        String email,
        @NotNull(message = "the field age is obligatory")
        @Min(value = 18, message = "Age must be greater than 18")
        int age,
        @NotNull(message = "the field salaryExpected is obligatory")
        int salaryExpected,
        String gender
) {

    public Candidate convertToEntity() {
        return Candidate
                .builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .age(age)
                .salaryExpected(salaryExpected)
                .gender(gender)
                .build();
    }

}
