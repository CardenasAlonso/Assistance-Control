package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.GradeEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GradeRepositoryPort {
    Mono<GradeEntity> save(GradeEntity gradeEntity);
    Mono<GradeEntity> update(GradeEntity gradeEntity);
    Mono<GradeEntity> findById(String id);
    Flux<GradeEntity> findAll();
    Mono<Void> deleteById(String id);
}