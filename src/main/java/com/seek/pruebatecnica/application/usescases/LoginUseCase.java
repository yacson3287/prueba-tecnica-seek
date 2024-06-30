package com.seek.pruebatecnica.application.usescases;

import com.seek.pruebatecnica.annotations.UseCase;
import com.seek.pruebatecnica.domain.entities.User;
import com.seek.pruebatecnica.domain.ports.in.ILoginUseCase;
import com.seek.pruebatecnica.domain.ports.out.IUserRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase implements ILoginUseCase {

    private final IUserRepository userRepository;

    @Override
    public String execute(User user) {
        return userRepository.login(user);
    }
}
