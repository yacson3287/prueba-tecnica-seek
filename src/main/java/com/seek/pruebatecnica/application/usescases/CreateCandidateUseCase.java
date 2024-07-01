package com.seek.pruebatecnica.application.usescases;

import com.seek.pruebatecnica.annotations.UseCase;
import com.seek.pruebatecnica.domain.entities.Candidate;
import com.seek.pruebatecnica.domain.exceptions.BadRequestExceptionService;
import com.seek.pruebatecnica.domain.exceptions.ExceptionDetail;
import com.seek.pruebatecnica.domain.ports.in.ICreateCandidateUseCase;
import com.seek.pruebatecnica.domain.ports.out.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class CreateCandidateUseCase implements ICreateCandidateUseCase {

    private final ICandidateRepository candidateRepository;

    @Override
    public Candidate execute(Candidate candidate) {
        validateEmail(candidate.getEmail());
        return candidateRepository.save(candidate);
    }

    private void validateEmail(String email) {
        var candidate = candidateRepository.findByEmail(email);
        if (candidate != null) {
            var exceptionDetail = new ExceptionDetail("It was not possible execute the action");
            exceptionDetail.addDetail("email", "This email it is use already for other user");
            throw new BadRequestExceptionService(exceptionDetail);
        }
    }
}
