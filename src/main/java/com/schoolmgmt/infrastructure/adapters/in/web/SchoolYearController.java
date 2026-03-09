package com.schoolmgmt.infrastructure.adapters.in.web;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.SchoolYearServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/api/school-years") @RequiredArgsConstructor
public class SchoolYearController {
    private final SchoolYearServicePort useCase;

    @PostMapping
    public Mono<ResponseEntity<SchoolYearResponseDto>> create(@RequestBody CreateSchoolYearCommand cmd) {
        return useCase.createYear(cmd).map(y -> ResponseEntity.status(HttpStatus.CREATED).body(y));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<SchoolYearResponseDto>> getById(@PathVariable String id) {
        return useCase.getYearById(id).map(ResponseEntity::ok);
    }
    @GetMapping
    public Flux<SchoolYearResponseDto> getAll() { return useCase.getAllYears(); }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<SchoolYearResponseDto>> update(@PathVariable String id, @RequestBody CreateSchoolYearCommand cmd) {
        return useCase.updateYear(id, cmd).map(ResponseEntity::ok);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return useCase.deleteYear(id).then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
