package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.SectionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionRepositoryPort {
    Mono<SectionEntity> save(SectionEntity sectionEntity);
    Mono<SectionEntity> update(SectionEntity sectionEntity);
    Mono<SectionEntity> findById(String id);
    Flux<SectionEntity> findAll();
    Mono<Void> deleteById(String id);
}