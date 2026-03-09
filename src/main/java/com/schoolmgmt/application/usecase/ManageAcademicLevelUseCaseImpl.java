package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.ports.in.AcademicLevelServicePort;
import com.schoolmgmt.application.ports.out.AcademicLevelRepositoryPort;
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
    public Mono<AcademicLevelEntity> createLevel(CreateAcademicLevelCommand command) {
        AcademicLevelEntity newLevel = AcademicLevelEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(command.name())
                .description(command.description())
                .isActive(1)
                .build();

        return repositoryPort.save(newLevel);
    }

    @Override
    public Flux<AcademicLevelEntity> getAllLevels() {
        return repositoryPort.findAll();
    }

    @Override
    public Mono<AcademicLevelEntity> getLevelById(String id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Mono<AcademicLevelEntity> updateLevel(String id, CreateAcademicLevelCommand command) {
        return repositoryPort.findById(id)
                .flatMap(existingLevel -> {
                    existingLevel.setName(command.name());
                    return repositoryPort.update(existingLevel);
                });
    }

    @Override
    public Mono<Void> deleteLevel(String id) {
        return repositoryPort.deleteById(id);
    }
}