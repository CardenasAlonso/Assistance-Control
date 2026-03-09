package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.ports.in.GradeServicePort;
import com.schoolmgmt.application.ports.out.GradeRepositoryPort;
import com.schoolmgmt.application.dto.CreateGradeCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.GradeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageGradeUseCaseImpl implements GradeServicePort {

    private final GradeRepositoryPort gradeRepositoryPort;

    @Override
    public Mono<GradeEntity> createGrade(CreateGradeCommand command) {
        GradeEntity newGrade = GradeEntity.builder()
                .id(UUID.randomUUID().toString())
                .academicLevelId(command.academicLevelId()) // Mapeamos el nivel
                .name(command.name()) // Mapeamos el nombre
                .gradeNumber(command.gradeNumber()) // Mapeamos el número
                .isActive(1)
                .build();

        return gradeRepositoryPort.save(newGrade);
    }

    @Override
    public Flux<GradeEntity> getAllGrades() {
        return gradeRepositoryPort.findAll();
    }

    @Override
    public Mono<GradeEntity> getGradeById(String id) {
        return gradeRepositoryPort.findById(id);
    }

    @Override
    public Mono<GradeEntity> updateGrade(String id, CreateGradeCommand command) {
        return gradeRepositoryPort.findById(id)
                .flatMap(existingGrade -> {
                    existingGrade.setName(command.name());
                    return gradeRepositoryPort.update(existingGrade);
                });
    }

    @Override
    public Mono<Void> deleteGrade(String id) {
        return gradeRepositoryPort.deleteById(id);
    }
}