package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.CreateTeacherCommand;
import com.schoolmgmt.application.dto.TeacherResponseDto;
import com.schoolmgmt.application.dto.UpdateTeacherCommand;
import com.schoolmgmt.application.usecase.ManageTeacherUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final ManageTeacherUseCaseImpl useCase;

    // Crear
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TeacherResponseDto> create(@RequestBody CreateTeacherCommand command) {
        return useCase.createTeacher(command);
    }

    // Obtener todos
    @GetMapping
    public Flux<TeacherResponseDto> getAll() {
        return useCase.getAllTeachers();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Mono<TeacherResponseDto> getById(@PathVariable String id) {
        return useCase.getTeacherById(id);
    }

    // Actualizar completo
    @PutMapping("/{id}")
    public Mono<TeacherResponseDto> update(@PathVariable String id, @RequestBody UpdateTeacherCommand command) {
        return useCase.updateTeacher(id, command);
    }

    // Desactivar (Soft Delete)
    @PatchMapping("/deactivate/{id}")
    public Mono<TeacherResponseDto> deactivate(@PathVariable String id) {
        return useCase.changeTeacherStatus(id, "INACTIVO");
    }

    // Reactivar
    @PatchMapping("/reactivate/{id}")
    public Mono<TeacherResponseDto> reactivate(@PathVariable String id) {
        return useCase.changeTeacherStatus(id, "ACTIVO");
    }

    // Eliminar permanentemente (Hard Delete)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.deleteTeacher(id);
    }

    // Manejo de errores
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleException(IllegalArgumentException ex) {
        // Retornamos 400 Bad Request con el mensaje amigable
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", ex.getMessage())));
    }
}