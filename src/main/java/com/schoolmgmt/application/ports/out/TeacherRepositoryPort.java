package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.TeacherEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherRepositoryPort {
    Mono<TeacherEntity> save(TeacherEntity teacher);
    Mono<TeacherEntity> update(TeacherEntity teacher);
    Mono<TeacherEntity> findById(String id);
    Flux<TeacherEntity> findAll();
    Mono<Void> deleteById(String id);
}