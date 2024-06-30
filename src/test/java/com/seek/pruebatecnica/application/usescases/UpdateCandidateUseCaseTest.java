package com.seek.pruebatecnica.application.usescases;

import com.google.gson.Gson;
import com.seek.pruebatecnica.domain.entities.Candidate;
import com.seek.pruebatecnica.domain.exceptions.BadRequestExceptionService;
import com.seek.pruebatecnica.domain.exceptions.ExceptionDetail;
import com.seek.pruebatecnica.domain.ports.out.ICandidateRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateCandidateUseCaseTest {

    @Mock
    ICandidateRepository candidateRepository;

    @InjectMocks
    UpdateCandidateUseCase updateCandidateUseCase;

    private Candidate candidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        candidate = buildCandidate();
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("should invoke the save method")
    @Test
    void executeSaveTest() {
        when(candidateRepository.findById(candidate.getId())).thenReturn(candidate);
        when(candidateRepository.save(candidate)).thenReturn(candidate);
        updateCandidateUseCase.execute(candidate);
        verify(candidateRepository).save(candidate);
    }

    @DisplayName("should return a exception")
    @Test
    void executeErrorTest() {
        when(candidateRepository.findById(candidate.getId())).thenReturn(null);
        Exception exception = Assertions.assertThrows(BadRequestExceptionService.class, () ->
                updateCandidateUseCase.execute(candidate)
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