package com.seek.pruebatecnica.domain.ports.in;

import com.seek.pruebatecnica.domain.entities.Candidate;

import java.util.List;

public interface IFindCandidateUseCase {

    Candidate executeById(Long id);

    List<Candidate> execute();
}
