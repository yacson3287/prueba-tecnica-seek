package com.seek.pruebatecnica.infrastructure.adapters.restcontrollers;

import com.seek.pruebatecnica.domain.ports.in.ICreateCandidateUseCase;
import com.seek.pruebatecnica.domain.ports.in.IDeleteCandidateUseCase;
import com.seek.pruebatecnica.domain.ports.in.IFindCandidateUseCase;
import com.seek.pruebatecnica.domain.ports.in.IUpdateCandidateUseCase;
import com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos.CandidateResponse;
import com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos.CreateCandidateRequest;
import com.seek.pruebatecnica.infrastructure.adapters.restcontrollers.dtos.UpdateCandidateRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/candidate")
@Tag(name = "candidates", description = "CRUD")
public class CandidateRestController {

    private final ICreateCandidateUseCase createCandidateUseCase;
    private final IUpdateCandidateUseCase updateCandidateUseCase;
    private final IFindCandidateUseCase findCandidateUseCase;
    private final IDeleteCandidateUseCase deleteCandidateUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CandidateResponse create(@RequestBody @Valid CreateCandidateRequest request) {
        var candidate = createCandidateUseCase.execute(request.convertToEntity());
        return CandidateResponse.convertFromEntity(candidate);
    }

    @PutMapping
    public CandidateResponse update(@RequestBody UpdateCandidateRequest request) {
        var candidate = updateCandidateUseCase.execute(request.convertToEntity());
        return CandidateResponse.convertFromEntity(candidate);
    }

    @GetMapping("{id}")
    public CandidateResponse fndById(@PathVariable Long id) {
        var candidate = findCandidateUseCase.executeById(id);
        return CandidateResponse.convertFromEntity(candidate);
    }

    @GetMapping
    public List<CandidateResponse> fndAll() {
        var candidates = findCandidateUseCase.execute();
        return candidates.stream()
                .map(CandidateResponse::convertFromEntity)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteCandidateUseCase.execute(id);
    }


}
