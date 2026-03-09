package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentGradesEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentGradesRepositoryPort {
    Mono<StudentGradesEntity> save(StudentGradesEntity studentGrades);
    Mono<StudentGradesEntity> findById(String id);
    Flux<StudentGradesEntity> findAll();
    Mono<Void> deleteById(String id);
    Mono<StudentGradesEntity> update(StudentGradesEntity studentGrades);
}