package com.seek.pruebatecnica.infrastructure.db.jpa.repositories;

import com.seek.pruebatecnica.infrastructure.db.jpa.model.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<UserJPA, Long> {

   UserJPA findByUsername(String username);
}
