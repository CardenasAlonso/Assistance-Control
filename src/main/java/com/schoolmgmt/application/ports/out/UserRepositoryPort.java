package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepositoryPort {
    Mono<UserEntity> save(UserEntity userEntity); // Para crear (INSERT)
    Mono<UserEntity> update(UserEntity userEntity); // Para actualizar (UPDATE)
    Mono<UserEntity> findById(String id);
    Mono<UserEntity> findByEmail(String email); // Búsqueda específica por negocio
    Flux<UserEntity> findAll(); // Devuelve una lista reactiva (Flux)
    Mono<Void> deleteById(String id);
}