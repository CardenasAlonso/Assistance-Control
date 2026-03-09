package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.GuardianResponseDto;
import com.schoolmgmt.application.dto.RegisterGuardianCommand;
import com.schoolmgmt.application.dto.UpdateGuardianCommand;
import com.schoolmgmt.application.usecase.ManageGuardianUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/guardians")
@RequiredArgsConstructor
public class GuardianController {

    private final ManageGuardianUseCaseImpl useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GuardianResponseDto> register(@RequestBody RegisterGuardianCommand command) {
        return useCase.registerGuardian(command);
    }

    @GetMapping
    public Flux<GuardianResponseDto> getAll() {
        return useCase.getAll();
    }

    @GetMapping("/{id}")
    public Mono<GuardianResponseDto> getById(@PathVariable String id) {
        return useCase.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@PathVariable String id, @RequestBody UpdateGuardianCommand command) {
        return useCase.updateGuardian(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.deleteGuardian(id);
    }
}