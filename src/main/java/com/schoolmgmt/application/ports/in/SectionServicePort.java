package com.schoolmgmt.application.ports.in;

import com.schoolmgmt.application.dto.CreateSectionCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.SectionEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionServicePort {
    Mono<SectionEntity> createSection(CreateSectionCommand command);
    Mono<SectionEntity> getSectionById(String id);
    Flux<SectionEntity> getAllSections();
    Mono<SectionEntity> updateSection(String id, CreateSectionCommand command);
    Mono<Void> deleteSection(String id);
}