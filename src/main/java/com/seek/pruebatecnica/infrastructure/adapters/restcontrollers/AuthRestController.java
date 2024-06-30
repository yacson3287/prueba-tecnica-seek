package com.seek.pruebatecnica.infrastructure.adapters.restcontrollers;

import com.seek.pruebatecnica.domain.ports.in.ILoginUseCase;
import com.seek.pruebatecnica.domain.ports.in.IRegisterUserUseCase;
import com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos.AuthResponse;
import com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos.LoginRequest;
import com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final IRegisterUserUseCase registerUserUseCase;
    private final ILoginUseCase loginUseCase;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return new AuthResponse(loginUseCase.execute(loginRequest.convertToEntity()));
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody LoginRequest loginRequest) {
        var user = registerUserUseCase.execute(loginRequest.convertToEntity());
        return UserResponse.convertFromUser(user);
    }

}
