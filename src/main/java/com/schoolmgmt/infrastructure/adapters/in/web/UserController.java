package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.UserResponseDto;
import com.schoolmgmt.application.usecase.ManageUserUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    // Inyectamos nuestro nuevo Caso de Uso
    private final ManageUserUseCaseImpl manageUserUseCase;

    // Obtener Usuario por ID
    @GetMapping("/{id}")
    public Mono<UserResponseDto> getUserById(@PathVariable String id) {
        return manageUserUseCase.getById(id);
    }

    // Obtener todos los Usuarios
    @GetMapping
    public Flux<UserResponseDto> getAllUsers() {
        return manageUserUseCase.getAll();
    }
    
    /* * NOTA ARQUITECTÓNICA:
     * Comentamos el POST (saveUser) intencionalmente. 
     * En nuestro sistema, los usuarios no se crean "vacíos". 
     * Se crean a través de RegisterStudentUseCase o RegisterTeacherUseCase 
     * para asegurar que siempre nazcan con un perfil asociado en la base de datos.
     */
}