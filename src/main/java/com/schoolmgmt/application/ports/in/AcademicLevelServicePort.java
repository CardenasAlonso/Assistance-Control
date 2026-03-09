package com.schoolmgmt.application.ports.in;

import com.schoolmgmt.application.dto.CreateAcademicLevelCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicLevelEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicLevelServicePort {
    Mono<AcademicLevelEntity> createLevel(CreateAcademicLevelCommand command);
    Flux<AcademicLevelEntity> getAllLevels();
    Mono<AcademicLevelEntity> getLevelById(String id);
    Mono<AcademicLevelEntity> updateLevel(String id, CreateAcademicLevelCommand command);
    Mono<Void> deleteLevel(String id);
}