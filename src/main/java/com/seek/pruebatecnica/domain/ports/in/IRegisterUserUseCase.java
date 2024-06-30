package com.seek.pruebatecnica.domain.ports.in;

import com.seek.pruebatecnica.domain.entities.User;

public interface IRegisterUserUseCase {
    User execute(User user);
}
