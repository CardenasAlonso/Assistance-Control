package com.schoolmgmt.application.ports.in;

import com.schoolmgmt.application.dto.CreateGradeCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.GradeEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GradeServicePort {
    Mono<GradeEntity> createGrade(CreateGradeCommand command);
    Flux<GradeEntity> getAllGrades();
    Mono<GradeEntity> getGradeById(String id);
    Mono<GradeEntity> updateGrade(String id, CreateGradeCommand command);
    Mono<Void> deleteGrade(String id);
}