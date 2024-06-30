package com.seek.pruebatecnica.domain.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private int age;
    private int salaryExpected;
    private String gender;
    private LocalDateTime createAt;
}
