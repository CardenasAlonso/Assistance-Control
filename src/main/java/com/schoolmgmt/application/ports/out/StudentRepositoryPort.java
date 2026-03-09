package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryPort {
    Mono<Void> save(StudentEntity student);
    Mono<StudentEntity> findById(String id);
    Flux<StudentEntity> findAll();
    Mono<Void> deleteById(String id);
}