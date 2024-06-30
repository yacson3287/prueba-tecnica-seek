package com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos;

import com.seek.pruebatecnica.domain.entities.User;

public record LoginRequest(
        String username,
        String password
) {

    public User convertToEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

}
