package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.SchoolYearEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchoolYearRepositoryPort {
    Mono<SchoolYearEntity> save(SchoolYearEntity schoolYear);
    Mono<SchoolYearEntity> findById(String id);
    Flux<SchoolYearEntity> findAll();
    Mono<Void> deleteById(String id);
    Mono<SchoolYearEntity> update(SchoolYearEntity schoolYear);
}