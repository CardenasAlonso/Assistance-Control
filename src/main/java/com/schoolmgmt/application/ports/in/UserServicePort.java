package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServicePort {
    Mono<UserResponseDto> getUserById(String id);
    Flux<UserResponseDto> getAllUsers();
    Mono<UserResponseDto> saveUser(UserResponseDto dto);
    Mono<Void> deleteUser(String id);
}
