package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.TeacherEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherRepositoryPort {
    Mono<Void> save(TeacherEntity teacher);
    Mono<TeacherEntity> findByEmail(String email);
    Mono<TeacherEntity> findById(String id);
    Flux<TeacherEntity> findAll();
    Mono<Void> deleteById(String id);
    Mono<Void> update(TeacherEntity teacher);
}