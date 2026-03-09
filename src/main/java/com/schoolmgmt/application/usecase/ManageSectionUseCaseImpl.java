package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.ports.in.SectionServicePort;
import com.schoolmgmt.application.ports.out.SectionRepositoryPort;
import com.schoolmgmt.application.dto.CreateSectionCommand;
import com.schoolmgmt.application.dto.SectionResponseDto;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.SectionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageSectionUseCaseImpl implements SectionServicePort {

    private final SectionRepositoryPort sectionRepositoryPort;

    @Override
    public Mono<SectionResponseDto> createSection(CreateSectionCommand command) {
        SectionEntity newSection = SectionEntity.builder()
                .id(UUID.randomUUID().toString())
                .gradeId(command.gradeId())
                .name(command.name())
                .capacity(command.capacity())
                .turn(command.turn())
                .isActive(1)
                .build();

        return sectionRepositoryPort.save(newSection);
    }

    @Override
    public Flux<SectionResponseDto> getAllSections() {
        return sectionRepositoryPort.findAll();
    }

    @Override
    public Mono<SectionResponseDto> getSectionById(String id) {
        return sectionRepositoryPort.findById(id);
    }

    @Override
    public Mono<SectionResponseDto> updateSection(String id, CreateSectionCommand command) {
        return sectionRepositoryPort.findById(id)
                .flatMap(existingSection -> {
                    SectionEntity updatedSection = SectionEntity.builder()
                            .id(existingSection.id())
                            .gradeId(command.gradeId())
                            .name(command.name())
                            .capacity(command.capacity())
                            .turn(command.turn())
                            .build();
                    return sectionRepositoryPort.update(updatedSection);
                });
    }

    @Override
    public Mono<Void> deleteSection(String id) {
        return sectionRepositoryPort.deleteById(id);
    }
}