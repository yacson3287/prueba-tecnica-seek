package com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos;

import com.seek.pruebatecnica.domain.entities.User;

public record UserResponse(
        Long id,
        String username
) {

    public static UserResponse convertFromUser(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
