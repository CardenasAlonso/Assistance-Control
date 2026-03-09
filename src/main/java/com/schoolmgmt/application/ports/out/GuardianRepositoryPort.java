package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.GuardianEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GuardianRepositoryPort {
    Mono<GuardianEntity> save(GuardianEntity guardian);
    Mono<GuardianEntity> update(GuardianEntity guardian);
    Mono<GuardianEntity> findById(String id);
    Flux<GuardianEntity> findAll();
    Mono<Void> deleteById(String id);
}