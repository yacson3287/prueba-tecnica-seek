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

import static org.mockito.Mockito.*;

class DeleteCandidateUseCaseTest {

    @Mock
    ICandidateRepository candidateRepository;

    @InjectMocks
    DeleteCandidateUseCase deleteCandidateUseCase;

    private Candidate candidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        candidate = buildCandidate();
    }

    @DisplayName("Should invoke the delete method")
    @Test
    void execute() {
        when(candidateRepository.findById(1L)).thenReturn(candidate);
        doNothing().when(candidateRepository).delete(candidate);
        deleteCandidateUseCase.execute(1L);
        verify(candidateRepository).delete(candidate);
    }

    @DisplayName("Should return a exception")
    @Test
    void executeError() {
        when(candidateRepository.findById(1L)).thenReturn(null);
        Exception exception = Assertions.assertThrows(BadRequestExceptionService.class, () ->
                deleteCandidateUseCase.execute(1L)
        );

        var exceptionDetail = new Gson().fromJson(exception.getMessage(), ExceptionDetail.class);
        Assertions.assertEquals("It is not exist candidate with this id", exceptionDetail.getDetails().get("id"));
    }

    private Candidate buildCandidate() {
        return Candidate.builder()
                .id(1L)
                .name("name")
                .lastName("lastname")
                .email("name@email.com")
                .gender("gender")
                .salaryExpected(1500)
                .build();
    }
}