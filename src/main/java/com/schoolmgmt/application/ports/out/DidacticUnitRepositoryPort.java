package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.domain.model.DidacticUnit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DidacticUnitRepositoryPort {
    Mono<DidacticUnit> save(DidacticUnit unit);
    Mono<DidacticUnit> findById(String id);
    Flux<DidacticUnit> findAll();
    Flux<DidacticUnit> findByCourseAssignmentId(String courseAssignmentId);
    Mono<DidacticUnit> update(DidacticUnit unit);
    Mono<Void> deleteById(String id);
}
