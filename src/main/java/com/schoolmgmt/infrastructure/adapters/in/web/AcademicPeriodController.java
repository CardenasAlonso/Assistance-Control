package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.AcademicPeriodResponseDto;
import com.schoolmgmt.application.dto.CreateAcademicPeriodCommand;
import com.schoolmgmt.application.dto.UpdateAcademicPeriodCommand;
import com.schoolmgmt.application.usecase.ManageAcademicPeriodUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/academic-periods")
@RequiredArgsConstructor
public class AcademicPeriodController {

    private final ManageAcademicPeriodUseCaseImpl useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AcademicPeriodResponseDto> create(@RequestBody CreateAcademicPeriodCommand command) {
        return useCase.createPeriod(command);
    }

    @GetMapping
    public Flux<AcademicPeriodResponseDto> getAll() {
        return useCase.getAllPeriods();
    }

    @GetMapping("/{id}")
    public Mono<AcademicPeriodResponseDto> getById(@PathVariable String id) {
        return useCase.getPeriodById(id);
    }

    @PutMapping("/{id}")
    public Mono<AcademicPeriodResponseDto> update(@PathVariable String id, @RequestBody UpdateAcademicPeriodCommand command) {
        return useCase.updatePeriod(id, command);
    }

    @PatchMapping("/deactivate/{id}")
    public Mono<AcademicPeriodResponseDto> deactivate(@PathVariable String id) {
        return useCase.changePeriodStatus(id, 0); // 0 = Inactivo
    }

    @PatchMapping("/reactivate/{id}")
    public Mono<AcademicPeriodResponseDto> reactivate(@PathVariable String id) {
        return useCase.changePeriodStatus(id, 1); // 1 = Activo
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.deletePeriod(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleException(IllegalArgumentException ex) {
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", ex.getMessage())));
    }
}