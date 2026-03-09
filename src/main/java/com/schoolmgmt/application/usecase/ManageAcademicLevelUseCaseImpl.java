package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.ports.in.AcademicLevelServicePort;
import com.schoolmgmt.application.ports.out.AcademicLevelRepositoryPort;
import com.schoolmgmt.application.dto.AcademicLevelResponseDto;
import com.schoolmgmt.application.dto.CreateAcademicLevelCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicLevelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageAcademicLevelUseCaseImpl implements AcademicLevelServicePort {

    private final AcademicLevelRepositoryPort repositoryPort;

    @Override
    public Mono<AcademicLevelResponseDto> createLevel(CreateAcademicLevelCommand command) {
        AcademicLevelEntity newLevel = AcademicLevelEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(command.name())
                .description(command.description())
                .isActive(1)
                .build();

        return repositoryPort.save(newLevel);
    }

    @Override
    public Flux<AcademicLevelResponseDto> getAllLevels() {
        return repositoryPort.findAll();
    }

    @Override
    public Mono<AcademicLevelResponseDto> getLevelById(String id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Mono<AcademicLevelResponseDto> updateLevel(String id, CreateAcademicLevelCommand command) {
        return repositoryPort.findById(id)
                .flatMap(existingLevel -> {
                    AcademicLevelEntity updatedLevel = AcademicLevelEntity.builder()
                            .id(existingLevel.id())
                            .name(command.name())
                            .description(command.description())
                            .isActive(existingLevel.isActive())
                            .build();
                    return repositoryPort.update(updatedLevel);
                });
    }

    @Override
    public Mono<Void> deleteLevel(String id) {
        return repositoryPort.deleteById(id);
    }
}