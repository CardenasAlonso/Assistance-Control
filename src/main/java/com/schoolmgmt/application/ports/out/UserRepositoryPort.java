package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.UserEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepositoryPort {
    Mono<Void> save(UserEntity user);
    Mono<UserEntity> findByEmail(String email);
    Mono<UserEntity> findById(String id);
    Flux<UserEntity> findAll();
    Mono<Void> deleteById(String id);
}
