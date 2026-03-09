package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseAssignmentEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseAssignmentRepositoryPort {
    Mono<CourseAssignmentEntity> save(CourseAssignmentEntity assignment);
    Mono<CourseAssignmentEntity> update(CourseAssignmentEntity entity);
    Flux<CourseAssignmentEntity> findAll();
    Flux<CourseAssignmentEntity> findByTeacherId(String teacherId);
    Mono<CourseAssignmentEntity> findById(String id);
}