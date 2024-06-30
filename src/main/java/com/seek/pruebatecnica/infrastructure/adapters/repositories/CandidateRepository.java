package com.seek.pruebatecnica.infrastructure.adapters.repositories;

import com.seek.pruebatecnica.domain.entities.Candidate;
import com.seek.pruebatecnica.domain.ports.out.ICandidateRepository;
import com.seek.pruebatecnica.infrastructure.db.jpa.model.CandidateJPA;
import com.seek.pruebatecnica.infrastructure.db.jpa.repositories.CandidateJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CandidateRepository implements ICandidateRepository {

    private final CandidateJPARepository candidateJPARepository;

    @Transactional
    @Override
    public Candidate save(Candidate candidate) {
        var candidateDB = candidateJPARepository.save(
                CandidateJPA.convertFromEntity(candidate)
        );
        return candidateDB.convertToEntity();
    }

    @Override
    public Candidate findById(Long id) {
        var candidate = candidateJPARepository.findById(id);
        return candidate.map(CandidateJPA::convertToEntity).orElse(null);
    }

    @Override
    public Candidate findByEmail(String email) {
        var candidate = candidateJPARepository.findByEmail(email);
        if (candidate == null) {
            return null;
        }
        return candidate.convertToEntity();
    }

    @Override
    public List<Candidate> findAll() {
        var candidates = candidateJPARepository.findAll();
        return candidates.stream()
                .map(CandidateJPA::convertToEntity)
                .toList();
    }

    @Transactional
    @Override
    public void delete(Candidate candidate) {
        candidateJPARepository.delete(
                CandidateJPA.convertFromEntity(candidate)
        );
    }
}
