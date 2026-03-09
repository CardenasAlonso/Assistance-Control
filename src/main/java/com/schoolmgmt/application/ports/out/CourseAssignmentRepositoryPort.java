package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseAssignmentEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseAssignmentRepositoryPort {
    Mono<CourseAssignmentEntity> save(CourseAssignmentEntity courseAssignment);
    Flux<CourseAssignmentEntity> findAll();
    Mono<CourseAssignmentEntity> findById(String id);
    Flux<CourseAssignmentEntity> findByTeacherId(String teacherId);
    Mono<CourseAssignmentEntity> update(CourseAssignmentEntity courseAssignment);
    Mono<Void> deleteById(String id);
}