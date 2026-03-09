package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.CreateAssignCourseCommand;
import com.schoolmgmt.application.dto.CourseAssignmentResponseDto;
import com.schoolmgmt.application.dto.CourseResponseDto;
import com.schoolmgmt.application.dto.CreateCourseCommand;
import com.schoolmgmt.application.usecase.ManageCourseUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ManageCourseUseCaseImpl useCase;

    // Endpoint para registrar el curso en el catálogo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CourseResponseDto> createCourse(@RequestBody CreateCourseCommand command) {
        return useCase.createCourse(command);
    }
    
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getAllCourses() {
        return useCase.getAllCourses()
                .collectList()
                .map(courses -> ResponseEntity.ok(Map.of(
                        "total", courses.size(),
                        "courses", courses
                )));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CourseResponseDto>> getCourseById(@PathVariable String id) {
        return useCase.getCourseById(id)
                .map(course -> ResponseEntity.ok(course))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    // Endpoint para asignar el curso a un profesor y una sección
    @PostMapping("/assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CourseAssignmentResponseDto> assignCourse(@RequestBody CreateAssignCourseCommand command) {
        return useCase.assignCourse(command);
    }

    // Manejador de errores para que Postman muestre un JSON limpio (Error 400)
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "Bad Request",
                        "message", ex.getMessage()
                )));
    }
}