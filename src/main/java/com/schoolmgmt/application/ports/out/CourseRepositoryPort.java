package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepositoryPort {
    Mono<CourseEntity> save(CourseEntity course);
    Flux<CourseEntity> findAll();
    Mono<CourseEntity> findById(String id);
    Mono<CourseEntity> update(CourseEntity course);
    Mono<Void> deleteById(String id);
}