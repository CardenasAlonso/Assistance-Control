package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicLevelEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicLevelRepositoryPort {
    Mono<AcademicLevelEntity> save(AcademicLevelEntity entity);
    Mono<AcademicLevelEntity> update(AcademicLevelEntity entity);
    Mono<AcademicLevelEntity> findById(String id);
    Flux<AcademicLevelEntity> findAll();
    Mono<Void> deleteById(String id);
}