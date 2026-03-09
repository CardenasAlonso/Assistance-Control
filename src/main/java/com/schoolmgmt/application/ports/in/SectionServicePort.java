package com.schoolmgmt.application.ports.in;

import com.schoolmgmt.application.dto.CreateSectionCommand;
import com.schoolmgmt.application.dto.SectionResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SectionServicePort {
    Mono<SectionResponseDto> createSection(CreateSectionCommand cmd);
    Mono<SectionResponseDto> getSectionById(String id);
    Flux<SectionResponseDto> getAllSections();
    Mono<SectionResponseDto> updateSection(String id, CreateSectionCommand cmd);
    Mono<Void> deleteSection(String id);
}
