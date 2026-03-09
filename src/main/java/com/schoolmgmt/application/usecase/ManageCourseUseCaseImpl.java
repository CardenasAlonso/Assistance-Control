package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.out.CourseAssignmentRepositoryPort;
import com.schoolmgmt.application.ports.out.CourseRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseAssignmentEntity;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageCourseUseCaseImpl {

    private final CourseRepositoryPort courseRepositoryPort;
    private final CourseAssignmentRepositoryPort assignmentRepositoryPort;

    // CREAR UN CURSO NUEVO
    public Mono<CourseResponseDto> createCourse(CreateCourseCommand command) {
        CourseEntity course = CourseEntity.builder()
                .id(UUID.randomUUID().toString())
                .academicLevelId(command.academicLevelId())
                .name(command.name())
                .code(command.code())
                .description(command.description())
                .credits(command.credits())
                .isActive(1) // 1 = Activo por defecto
                .createdAt(LocalDateTime.now())
                .build();

        return courseRepositoryPort.save(course)
                // Atrapamos si el academicLevelId no existe en la BD o si el código ya está en uso
                .onErrorResume(DataIntegrityViolationException.class,
                        e -> Mono.error(new IllegalArgumentException(
                                "Error: El nivel académico no existe o el código del curso ya está registrado.")))
                .map(this::mapToCourseDto);
    }

    // OBTENER TODOS LOS CURSOS
    public Flux<CourseResponseDto> getAllCourses() {
        return courseRepositoryPort.findAll()
                .map(this::mapToCourseDto);
    }

     // OBTENER UN CURSO POR ID
    public Mono<CourseResponseDto> getCourseById(String id) {
        return courseRepositoryPort.findById(id)
                .map(this::mapToCourseDto)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error: Curso no encontrado con ID: " + id)));
    }
    

    // ASIGNAR UN CURSO (Profesor + Curso + Sección)
    public Mono<CourseAssignmentResponseDto> assignCourse(CreateAssignCourseCommand command) {
        CourseAssignmentEntity assignment = CourseAssignmentEntity.builder()
                .id(UUID.randomUUID().toString())
                .teacherId(command.teacherId())
                .courseId(command.courseId())
                .sectionId(command.sectionId())
                .academicPeriodId(command.academicPeriodId())
                .hoursPerWeek(command.hoursPerWeek())
                .build();

        return assignmentRepositoryPort.save(assignment)
                // Atrapamos si el profesor, el curso o la sección no existen
                .onErrorResume(DataIntegrityViolationException.class,
                        e -> Mono.error(new IllegalArgumentException(
                                "Error: El profesor, el curso o la sección indicados no existen en la base de datos.")))
                .map(this::mapToAssignmentDto);
    }

    

    // PARA EL MAPEO
    private CourseResponseDto mapToCourseDto(CourseEntity entity) {
        return new CourseResponseDto(
                entity.getId(),
                entity.getAcademicLevelId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getCredits(),
                entity.getIsActive(),
                entity.getCreatedAt());
    }

    private CourseAssignmentResponseDto mapToAssignmentDto(CourseAssignmentEntity entity) {
        return new CourseAssignmentResponseDto(
                entity.getId(),
                entity.getTeacherId(),
                entity.getCourseId(),
                entity.getSectionId(),
                entity.getAcademicPeriodId(),
                entity.getHoursPerWeek());
    }
}