package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.domain.model.StudentScore;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentScoreRepositoryPort {
    Mono<StudentScore> save(StudentScore score);
    Mono<StudentScore> findById(String id);
    Flux<StudentScore> findAll();
    Flux<StudentScore> findByStudentId(String studentId);
    Flux<StudentScore> findByCourseAssignmentId(String courseAssignmentId);
    Flux<StudentScore> findByDidacticUnitId(String didacticUnitId);
    Mono<StudentScore> update(StudentScore score);
    Mono<Void> deleteById(String id);
}
