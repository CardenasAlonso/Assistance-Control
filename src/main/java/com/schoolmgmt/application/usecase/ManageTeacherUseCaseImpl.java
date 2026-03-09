package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.CreateTeacherCommand;
import com.schoolmgmt.application.dto.TeacherResponseDto;
import com.schoolmgmt.application.dto.UpdateTeacherCommand;
import com.schoolmgmt.application.ports.out.TeacherRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.TeacherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageTeacherUseCaseImpl {

    private final TeacherRepositoryPort repositoryPort;

    // CREAR
    public Mono<TeacherResponseDto> createTeacher(CreateTeacherCommand command) {
        TeacherEntity entity = TeacherEntity.builder()
                .id(UUID.randomUUID().toString())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .documentNumber(command.documentNumber())
                .email(command.email())
                .phone(command.phone())
                .specialtiesJson(command.specialtiesJson())
                .status("ACTIVO") // Por defecto al crear
                .hireDate(command.hireDate())
                .build();

        return repositoryPort.save(entity)
                .onErrorResume(DataIntegrityViolationException.class, e -> 
                        Mono.error(new IllegalArgumentException("Error: El documento o correo ya existen.")))
                .then(repositoryPort.findById(entity.getId())) // Para obtener el registro completo con ID generado
                .map(this::mapToDto);
    }

    // LEER TODOS
    public Flux<TeacherResponseDto> getAllTeachers() {
        return repositoryPort.findAll().map(this::mapToDto);
    }

    // LEER POR ID
    public Mono<TeacherResponseDto> getTeacherById(String id) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Profesor no encontrado.")))
                .map(this::mapToDto);
    }

    // ACTUALIZAR (Reemplaza datos sin tocar ID, status ni userId)
    public Mono<TeacherResponseDto> updateTeacher(String id, UpdateTeacherCommand command) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Profesor no encontrado.")))
                .flatMap(existingTeacher -> {
                    existingTeacher.setFirstName(command.firstName());
                    existingTeacher.setLastName(command.lastName());
                    existingTeacher.setDocumentNumber(command.documentNumber());
                    existingTeacher.setEmail(command.email());
                    existingTeacher.setPhone(command.phone());
                    existingTeacher.setSpecialtiesJson(command.specialtiesJson());
                    existingTeacher.setHireDate(command.hireDate());
                    
                    return repositoryPort.update(existingTeacher)
                            .onErrorResume(DataIntegrityViolationException.class, e -> 
                                    Mono.error(new IllegalArgumentException("Error: El documento o correo ya existen en otro registro.")));
                })
                .then(repositoryPort.findById(id)) // Para obtener el registro actualizado
                .map(this::mapToDto);
    }

    // CAMBIAR ESTADO (Desactivar / Reactivar)
    public Mono<TeacherResponseDto> changeTeacherStatus(String id, String newStatus) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Profesor no encontrado.")))
                .flatMap(existingTeacher -> {
                    existingTeacher.setStatus(newStatus);
                    return repositoryPort.update(existingTeacher);
                })
                .then(repositoryPort.findById(id)) // Para obtener el registro actualizado
                .map(this::mapToDto);
    }

    // ELIMINAR (Hard Delete)
    public Mono<Void> deleteTeacher(String id) {
        return repositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Profesor no encontrado.")))
                .flatMap(teacher -> repositoryPort.deleteById(teacher.getId()));
    }

    // --- MAPPER ---
    private TeacherResponseDto mapToDto(TeacherEntity entity) {
        return new TeacherResponseDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDocumentNumber(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getSpecialtiesJson(),
                entity.getStatus(),
                entity.getHireDate()
        );
    }
}