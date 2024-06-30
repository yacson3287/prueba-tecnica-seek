package com.seek.pruebatecnica.domain.ports.out;

import com.seek.pruebatecnica.domain.entities.Candidate;

import java.util.List;

public interface ICandidateRepository {

    Candidate save(Candidate candidate);

    Candidate findById(Long id);

    Candidate findByEmail(String email);

    List<Candidate> findAll();

    void delete(Candidate candidate);
}
