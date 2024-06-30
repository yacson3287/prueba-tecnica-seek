package com.seek.pruebatecnica.application.usescases;

import com.seek.pruebatecnica.annotations.UseCase;
import com.seek.pruebatecnica.domain.entities.User;
import com.seek.pruebatecnica.domain.exceptions.BadRequestExceptionService;
import com.seek.pruebatecnica.domain.exceptions.ExceptionDetail;
import com.seek.pruebatecnica.domain.ports.in.IRegisterUserUseCase;
import com.seek.pruebatecnica.domain.ports.out.IUserRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterUserUseCase implements IRegisterUserUseCase {

    private final IUserRepository userRepository;

    @Override
    public User execute(User user) {
        validateUsername(user.getUsername());
        return userRepository.save(user);
    }

    private void validateUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            var exceptionDetail = new ExceptionDetail("It was not possible execute the action");
            exceptionDetail.addDetail("email", "This username it is use already for other user");
            throw new BadRequestExceptionService(exceptionDetail);
        }
    }

}
