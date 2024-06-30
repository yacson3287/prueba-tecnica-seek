package com.seek.pruebatecnica.infrastructure.db.jpa.repositories;

import com.seek.pruebatecnica.infrastructure.db.jpa.model.CandidateJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateJPARepository extends JpaRepository<CandidateJPA, Long> {

    CandidateJPA findByEmail(String email);
}
