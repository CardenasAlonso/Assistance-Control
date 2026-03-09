package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.domain.model.Justification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JustificationRepositoryPort {
    Mono<Justification> save(Justification justification);
    Mono<Justification> findById(String id);
    Flux<Justification> findByStudentId(String studentId);
    Flux<Justification> findByStatus(String status);
    Mono<Justification> update(Justification justification);
    Mono<Void> deleteById(String id);
}
