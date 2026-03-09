/*package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.CourseAssignmentResponseDto;
import com.schoolmgmt.application.dto.RegisterCourseAssignmentCommand;
import com.schoolmgmt.application.dto.UpdateCourseAssignmentCommand;
import com.schoolmgmt.application.usecase.ManageCourseAssignmentUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/course-assignments")
@RequiredArgsConstructor
public class CourseAssignmentController {
    
    private final ManageCourseAssignmentUseCaseImpl useCase;

    // Endpoint para registrar la asignación del curso a un profesor y una sección
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CourseAssignmentResponseDto> register(@RequestBody RegisterCourseAssignmentCommand command) { 
        return useCase.register(command); 
    }

    @PutMapping("/{id}")
    public Mono<CourseAssignmentResponseDto> update(@PathVariable String id, @RequestBody UpdateCourseAssignmentCommand command) {
        return useCase.update(id, command);
    }

    @GetMapping
    public Flux<CourseAssignmentResponseDto> getAll() { 
        return useCase.getAll(); 
    }

    @GetMapping("/{id}")
    public Mono<CourseAssignmentResponseDto> getById(@PathVariable String id) {
        return useCase.getById(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public Flux<CourseAssignmentResponseDto> getByTeacherId(@PathVariable String teacherId) {
        return useCase.getByTeacherId(teacherId);
    }
}*/