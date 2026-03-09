package com.schoolmgmt.application.ports.in;

import com.schoolmgmt.application.dto.CreateUserCommand;
import com.schoolmgmt.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServicePort {
    Mono<User> getUserById(String id);
    Flux<User> getAllUsers();
    Mono<User> saveUser(CreateUserCommand command);
    Mono<Void> deleteUser(String id);
}