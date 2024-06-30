package com.seek.pruebatecnica.domain.ports.in;

import com.seek.pruebatecnica.domain.entities.Candidate;

public interface IUpdateCandidateUseCase {
    Candidate execute(Candidate candidate);
}
