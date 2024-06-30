package com.seek.pruebatecnica.domain.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
}
