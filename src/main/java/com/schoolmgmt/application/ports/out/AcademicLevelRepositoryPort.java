package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.application.dto.AcademicLevelResponseDto;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicLevelEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicLevelRepositoryPort {
    Mono<AcademicLevelResponseDto> save(AcademicLevelEntity level);
    Flux<AcademicLevelResponseDto> findAll();
    Mono<AcademicLevelResponseDto> findById(String id);
    Mono<AcademicLevelResponseDto> update(AcademicLevelEntity level);
    Mono<Void> deleteById(String id);
}