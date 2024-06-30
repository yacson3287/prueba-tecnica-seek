package com.seek.pruebatecnica.domain.ports.out;

import com.seek.pruebatecnica.domain.entities.User;

public interface IUserRepository {

    User findByUsername(String username);

    User save(User user);

    String login(User user);
}
