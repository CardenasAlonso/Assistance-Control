package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicPeriodEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicPeriodRepositoryPort {
    Mono<AcademicPeriodEntity> save(AcademicPeriodEntity academicPeriod);
    Mono<AcademicPeriodEntity> findById(String id);
    Flux<AcademicPeriodEntity> findAll();
    Mono<Void> deleteById(String id);
    Mono<AcademicPeriodEntity> update(AcademicPeriodEntity academicPeriod);
}