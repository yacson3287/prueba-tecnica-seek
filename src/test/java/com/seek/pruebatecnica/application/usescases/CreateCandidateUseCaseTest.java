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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateCandidateUseCaseTest {

    @Mock
    ICandidateRepository candidateRepository;

    @InjectMocks
    CreateCandidateUseCase createCandidateUseCase;

    private Candidate candidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        candidate = buildCandidate();
    }

    @DisplayName("should invoke the save method")
    @Test
    void executeSaveTest() {
        when(candidateRepository.findByEmail(candidate.getEmail())).thenReturn(null);
        when(candidateRepository.save(candidate)).thenReturn(candidate);
        createCandidateUseCase.execute(candidate);
        verify(candidateRepository).save(candidate);
    }

    @DisplayName("should return a exception")
    @Test
    void executeErrorTest() {
        when(candidateRepository.findByEmail(candidate.getEmail())).thenReturn(new Candidate());
        Exception exception = Assertions.assertThrows(BadRequestExceptionService.class, () ->
                createCandidateUseCase.execute(candidate)
        );

        var exceptionDetail = new Gson().fromJson(exception.getMessage(), ExceptionDetail.class);
        Assertions.assertEquals("This email it is use already for other user", exceptionDetail.getDetails().get("email"));

    }

    private Candidate buildCandidate() {
        return Candidate.builder()
                .name("name")
                .lastName("lastname")
                .email("name@email.com")
                .gender("gender")
                .salaryExpected(1500)
                .build();
    }


}