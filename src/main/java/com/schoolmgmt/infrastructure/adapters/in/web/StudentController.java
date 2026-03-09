package com.schoolmgmt.infrastructure.adapters.in.web;
import com.schoolmgmt.application.ports.in.ManageStudentUseCase;

import com.schoolmgmt.application.dto.RegisterStudentCommand;
import com.schoolmgmt.application.dto.StudentResponseDto;
import com.schoolmgmt.application.dto.UpdateStudentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final ManageStudentUseCase studentUseCase;

    // Crear Estudiante (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public Mono<StudentResponseDto> registerStudent(@RequestBody RegisterStudentCommand command) { // <-- Ahora devuelve el DTO
        return studentUseCase.registerStudent(command);
    }

    // Obtener Estudiante por ID (GET)
    @GetMapping("/{id}")
    public Mono<StudentResponseDto> getStudentById(@PathVariable String id) {
        return studentUseCase.getById(id);
    }

    // Obtener todos los Estudiantes (GET)
    @GetMapping
    public Flux<StudentResponseDto> getAllStudents() {
        return studentUseCase.getAll();
    }

    // Actualizar Estudiante (PUT)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public Mono<Void> updateStudent(@PathVariable String id, @RequestBody UpdateStudentCommand command) {
        return studentUseCase.updateStudent(id, command).then();
    }
    
    // Eliminar Estudiante (DELETE) - ¡Descomentado y arreglado!
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public Mono<Void> deleteStudent(@PathVariable String id) {
        return studentUseCase.deleteStudent(id); // <-- Llama correctamente a tu UseCase
    }
}