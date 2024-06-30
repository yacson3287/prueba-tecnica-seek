package com.seek.pruebatecnica.application.usescases;

import com.google.gson.Gson;
import com.seek.pruebatecnica.domain.entities.Candidate;
import com.seek.pruebatecnica.domain.exceptions.BadRequestExceptionService;
import com.seek.pruebatecnica.domain.exceptions.ExceptionDetail;
import com.seek.pruebatecnica.domain.ports.out.ICandidateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class FindCandidateUseCaseTest {

    @Mock
    ICandidateRepository candidateRepository;

    @InjectMocks
    FindCandidateUseCase findCandidateUseCase;

    private List<Candidate> candidates;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        candidates = buildCandidate();
    }


    @DisplayName("Should return a candidate with id 1")
    @Test
    void executeById() {
        when(candidateRepository.findById(1L)).thenReturn(candidates.get(1));
        var candidate = findCandidateUseCase.executeById(1L);
        Assertions.assertEquals(candidates.get(1).getId(), candidate.getId());
    }

    @DisplayName("should return a list the candidates")
    @Test
    void execute() {
        when(candidateRepository.findAll()).thenReturn(candidates);
        var result = findCandidateUseCase.execute();
        Assertions.assertEquals(candidates.size(), result.size());
    }

    @DisplayName("Should return a Exception")
    @Test
    void executeByIdError() {
        when(candidateRepository.findById(1L)).thenReturn(null);
        Exception exception = Assertions.assertThrows(BadRequestExceptionService.class, () -> findCandidateUseCase.executeById(1L));
        var exceptionDetail = new Gson().fromJson(exception.getMessage(), ExceptionDetail.class);
        Assertions.assertEquals("It is not exist candidate with this id", exceptionDetail.getDetails().get("id"));
    }


    private List<Candidate> buildCandidate() {
        List<Candidate> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var candidate = Candidate.builder()
                    .id((long) i)
                    .name("name")
                    .lastName("lastname")
                    .email("name@email.com")
                    .gender("gender")
                    .salaryExpected(1500)
                    .build();
            list.add(candidate);
        }
        return list;
    }
}