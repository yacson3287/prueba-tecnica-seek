package com.seek.pruebatecnica.infrastructure.adapters.repositories;

import com.seek.pruebatecnica.domain.entities.User;
import com.seek.pruebatecnica.domain.ports.out.IUserRepository;
import com.seek.pruebatecnica.infrastructure.configurations.security.JwtService;
import com.seek.pruebatecnica.infrastructure.db.jpa.model.Role;
import com.seek.pruebatecnica.infrastructure.db.jpa.model.UserJPA;
import com.seek.pruebatecnica.infrastructure.db.jpa.repositories.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserJPARepository userJPARepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User findByUsername(String username) {
        var user = userJPARepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return convertToEntity(user);
    }

    @Override
    public User save(User user) {
        var userJpa = userJPARepository.save(convertFromEntity(user));
        return convertToEntity(userJpa);
    }

    @Override
    public String login(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetail = userJPARepository.findByUsername(user.getUsername());
        return jwtService.getToken(userDetail);
    }


    private User convertToEntity(UserJPA userJPA) {
        return User.builder()
                .id(userJPA.getId())
                .username(userJPA.getUsername())
                .password(userJPA.getPassword())
                .role(userJPA.getRole().toString())
                .build();
    }

    private UserJPA convertFromEntity(User user) {
        return UserJPA.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.ADMIN)
                .build();
    }
}
