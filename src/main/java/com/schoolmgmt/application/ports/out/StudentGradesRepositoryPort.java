package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentGradesEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentGradesRepositoryPort {
    Mono<StudentGradesEntity> save(StudentGradesEntity grade);
    Mono<StudentGradesEntity> update(StudentGradesEntity grade);
    Mono<StudentGradesEntity> findById(String id);
    Flux<StudentGradesEntity> findAll();
    Mono<Void> deleteById(String id);
}