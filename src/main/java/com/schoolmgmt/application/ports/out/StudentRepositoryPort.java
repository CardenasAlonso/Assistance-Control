package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryPort {
    Mono<StudentEntity> save(StudentEntity studentEntity);
    Mono<StudentEntity> update(StudentEntity studentEntity);
    Mono<StudentEntity> findById(String id);
    Mono<StudentEntity> findByDocumentNumber(String documentNumber); // Búsqueda por DNI
    Flux<StudentEntity> findAll();
    Mono<Void> deleteById(String id);
}