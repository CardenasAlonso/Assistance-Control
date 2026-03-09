package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.AcademicPeriodResponseDto;
import com.schoolmgmt.application.dto.CreateAcademicPeriodCommand;
import com.schoolmgmt.application.dto.UpdateAcademicPeriodCommand;
import com.schoolmgmt.application.ports.out.AcademicPeriodRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicPeriodEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageAcademicPeriodUseCaseImpl {

    private final AcademicPeriodRepositoryPort repositoryPort;

    // CREAR
    public Mono<AcademicPeriodResponseDto> createPeriod(CreateAcademicPeriodCommand command) {
        // Validamos que la fecha de fin sea posterior a la de inicio
        if (command.endDate().isBefore(command.startDate())) {
            return Mono.error(new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio."));
        }

        AcademicPeriodEntity entity = AcademicPeriodEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(command.name())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .isActive(1) // 1 = Activo
                .createdAt(LocalDateTime.now())
                .build();

        return repositoryPort.save(entity)
                .map(this::mapToDto);
    }

    // LEER TODOS
    public Flux<AcademicPeriodResponseDto> getAllPeriods() {
        return repositoryPort.findAll().map(this::mapToDto);
    }

    // LEER POR ID
    public Mono<AcademicPeriodResponseDto> getPeriodById(String id) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Periodo académico no encontrado.")))
                .map(this::mapToDto);
    }

    // ACTUALIZAR
    public Mono<AcademicPeriodResponseDto> updatePeriod(String id, UpdateAcademicPeriodCommand command) {
        if (command.endDate().isBefore(command.startDate())) {
            return Mono.error(new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio."));
        }

        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Periodo académico no encontrado.")))
                .flatMap(existingPeriod -> {
                    existingPeriod.setName(command.name());
                    existingPeriod.setStartDate(command.startDate());
                    existingPeriod.setEndDate(command.endDate());
                    
                    return repositoryPort.update(existingPeriod);
                })
                .map(this::mapToDto);
    }

    // CAMBIAR ESTADO (1 = Activo, 0 = Inactivo)
    public Mono<AcademicPeriodResponseDto> changePeriodStatus(String id, Integer newStatus) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Periodo académico no encontrado.")))
                .flatMap(existingPeriod -> {
                    existingPeriod.setIsActive(newStatus);
                    return repositoryPort.update(existingPeriod);
                })
                .map(this::mapToDto);
    }

    // ELIMINAR
    public Mono<Void> deletePeriod(String id) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Periodo académico no encontrado.")))
                .flatMap(period -> repositoryPort.deleteById(period.getId()))
                .onErrorResume(DataIntegrityViolationException.class, e -> 
                        Mono.error(new IllegalArgumentException("No se puede eliminar el periodo porque ya tiene cursos asignados. Intenta desactivarlo.")));
    }

    // --- MAPPER ---
    private AcademicPeriodResponseDto mapToDto(AcademicPeriodEntity saved) {
        return new AcademicPeriodResponseDto(
                saved.getId(), saved.getName(), saved.getStartDate(), 
                saved.getEndDate(), saved.getIsActive(), saved.getCreatedAt()
        );
    }
}