package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.CourseAssignmentResponseDto;
import com.schoolmgmt.application.dto.RegisterCourseAssignmentCommand;
import com.schoolmgmt.application.dto.UpdateCourseAssignmentCommand;
import com.schoolmgmt.application.ports.out.CourseAssignmentRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseAssignmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageCourseAssignmentUseCaseImpl {
    
    private final CourseAssignmentRepositoryPort port;

    // Crear
    public Mono<CourseAssignmentResponseDto> register(RegisterCourseAssignmentCommand command) {
        CourseAssignmentEntity entity = CourseAssignmentEntity.builder()
                .id(UUID.randomUUID().toString())
                .teacherId(command.teacherId())
                .courseId(command.courseId())
                .sectionId(command.sectionId())
                .academicPeriodId(command.academicPeriodId())
                .hoursPerWeek(command.hoursPerWeek())
                .build();
                
        return port.save(entity).map(this::mapToDto);
    }

    // Actualizar
    public Mono<CourseAssignmentResponseDto> update(String id, UpdateCourseAssignmentCommand command) {
        return port.findById(id)
                .map(entity -> {
                    entity.setTeacherId(command.teacherId());
                    entity.setCourseId(command.courseId());
                    entity.setSectionId(command.sectionId());
                    entity.setAcademicPeriodId(command.academicPeriodId());
                    entity.setHoursPerWeek(command.hoursPerWeek());
                    return entity;
                })
                .flatMap(port::update)
                .map(this::mapToDto);
    }

    // Listar todos
    public Flux<CourseAssignmentResponseDto> getAll() { 
        return port.findAll().map(this::mapToDto); 
    }

    // Buscar por ID
    public Mono<CourseAssignmentResponseDto> getById(String id) {
        return port.findById(id).map(this::mapToDto);
    }

    // Buscar por Profesor
    public Flux<CourseAssignmentResponseDto> getByTeacherId(String teacherId) {
        return port.findByTeacherId(teacherId).map(this::mapToDto);
    }

    // Mapeador auxiliar
    private CourseAssignmentResponseDto mapToDto(CourseAssignmentEntity e) {
        return new CourseAssignmentResponseDto(
            e.getId(), e.getTeacherId(), e.getCourseId(), 
            e.getSectionId(), e.getAcademicPeriodId(), e.getHoursPerWeek()
        );
    }
}