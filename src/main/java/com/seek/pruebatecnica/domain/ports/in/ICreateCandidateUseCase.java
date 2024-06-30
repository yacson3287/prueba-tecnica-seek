package com.seek.pruebatecnica.domain.ports.in;

import com.seek.pruebatecnica.domain.entities.Candidate;

public interface ICreateCandidateUseCase {

    Candidate execute(Candidate candidate);
}
