package com.seek.pruebatecnica.application.usescases;

import com.seek.pruebatecnica.annotations.UseCase;
import com.seek.pruebatecnica.domain.entities.Candidate;
import com.seek.pruebatecnica.domain.exceptions.BadRequestExceptionService;
import com.seek.pruebatecnica.domain.exceptions.ExceptionDetail;
import com.seek.pruebatecnica.domain.ports.in.IDeleteCandidateUseCase;
import com.seek.pruebatecnica.domain.ports.out.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class DeleteCandidateUseCase implements IDeleteCandidateUseCase {

    private final ICandidateRepository candidateRepository;

    @Override
    public void execute(Long id) {
        var candidate = findCandidate(id);
        candidateRepository.delete(candidate);
        log.info("It was delete candidate witch id: {}", id);
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
}
