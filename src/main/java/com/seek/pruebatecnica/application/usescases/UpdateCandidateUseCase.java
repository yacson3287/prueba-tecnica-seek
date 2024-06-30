package com.seek.pruebatecnica.application.usescases;

import com.seek.pruebatecnica.annotations.UseCase;
import com.seek.pruebatecnica.domain.entities.Candidate;
import com.seek.pruebatecnica.domain.exceptions.BadRequestExceptionService;
import com.seek.pruebatecnica.domain.exceptions.ExceptionDetail;
import com.seek.pruebatecnica.domain.ports.in.IUpdateCandidateUseCase;
import com.seek.pruebatecnica.domain.ports.out.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateCandidateUseCase implements IUpdateCandidateUseCase {

    private final ICandidateRepository candidateRepository;

    @Override
    public Candidate execute(Candidate candidate) {
        var currentCandidate = findCandidate(candidate.getId());
        mapper(currentCandidate, candidate);
        return candidateRepository.save(currentCandidate);
    }


    private Candidate findCandidate(Long id) {
        var candidate = candidateRepository.findById(id);
        if (candidate == null) {
            var exceptionDetail = new ExceptionDetail("It was not possible execute the action");
            exceptionDetail.addDetail("id", "It is not exist candidate with this id");
            throw new BadRequestExceptionService(exceptionDetail);
        }
        return candidate;
    }

    private void mapper(Candidate currentCandidate, Candidate candidate) {
        currentCandidate.setAge(candidate.getAge());
        currentCandidate.setName(candidate.getName());
        currentCandidate.setLastName(candidate.getLastName());
        currentCandidate.setGender(currentCandidate.getGender());
    }
}
