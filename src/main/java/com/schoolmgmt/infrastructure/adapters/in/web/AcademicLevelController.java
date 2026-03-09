package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.ports.in.AcademicLevelServicePort;
import com.schoolmgmt.application.dto.CreateAcademicLevelCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicLevelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic-levels")
@RequiredArgsConstructor
public class AcademicLevelController {

    private final AcademicLevelServicePort servicePort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AcademicLevelEntity> createLevel(@RequestBody CreateAcademicLevelCommand command) {
        return servicePort.createLevel(command);
    }

    @GetMapping
    public Flux<AcademicLevelEntity> getAllLevels() {
        return servicePort.getAllLevels();
    }

    @GetMapping("/{id}")
    public Mono<AcademicLevelEntity> getLevelById(@PathVariable String id) {
        return servicePort.getLevelById(id);
    }

    @PutMapping("/{id}")
    public Mono<AcademicLevelEntity> updateLevel(@PathVariable String id, @RequestBody CreateAcademicLevelCommand command) {
        return servicePort.updateLevel(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteLevel(@PathVariable String id) {
        return servicePort.deleteLevel(id);
    }
}