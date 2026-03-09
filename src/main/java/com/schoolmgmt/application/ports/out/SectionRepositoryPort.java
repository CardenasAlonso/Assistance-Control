package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.application.dto.SectionResponseDto;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.SectionEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionRepositoryPort {
    Mono<SectionResponseDto> save(SectionEntity section);
    Mono<SectionResponseDto> findById(String id);
    Flux<SectionResponseDto> findAll();
    Mono<Void> deleteById(String id);
    Mono<SectionResponseDto> update(SectionEntity section);
}