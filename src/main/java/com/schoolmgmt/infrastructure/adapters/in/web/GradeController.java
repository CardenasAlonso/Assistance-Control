package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.ports.in.GradeServicePort;
import com.schoolmgmt.application.dto.CreateGradeCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.GradeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeServicePort gradeServicePort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GradeEntity> createGrade(@RequestBody CreateGradeCommand command) {
        return gradeServicePort.createGrade(command);
    }

    @GetMapping
    public Flux<GradeEntity> getAllGrades() {
        return gradeServicePort.getAllGrades();
    }

    @GetMapping("/{id}")
    public Mono<GradeEntity> getGradeById(@PathVariable String id) {
        return gradeServicePort.getGradeById(id);
    }

    @PutMapping("/{id}")
    public Mono<GradeEntity> updateGrade(@PathVariable String id, @RequestBody CreateGradeCommand command) {
        return gradeServicePort.updateGrade(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteGrade(@PathVariable String id) {
        return gradeServicePort.deleteGrade(id);
    }
}