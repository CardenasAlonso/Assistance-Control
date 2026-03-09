package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.ports.in.SectionServicePort;
import com.schoolmgmt.application.ports.out.SectionRepositoryPort;
import com.schoolmgmt.application.dto.CreateSectionCommand;
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
    public Mono<SectionEntity> createSection(CreateSectionCommand command) {
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
    public Flux<SectionEntity> getAllSections() {
        return sectionRepositoryPort.findAll();
    }

    @Override
    public Mono<SectionEntity> getSectionById(String id) {
        return sectionRepositoryPort.findById(id);
    }

    @Override
    public Mono<SectionEntity> updateSection(String id, CreateSectionCommand command) {
        return sectionRepositoryPort.findById(id)
                .flatMap(existingSection -> {
                    existingSection.setGradeId(command.gradeId());
                    existingSection.setName(command.name());
                    existingSection.setCapacity(command.capacity());
                    existingSection.setTurn(command.turn());
                    
                    return sectionRepositoryPort.update(existingSection);
                });
    }

    @Override
    public Mono<Void> deleteSection(String id) {
        return sectionRepositoryPort.deleteById(id);
    }
}