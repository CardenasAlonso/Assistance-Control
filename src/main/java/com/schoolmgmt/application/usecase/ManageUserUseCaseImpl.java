package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.UserResponseDto;
import com.schoolmgmt.application.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ManageUserUseCaseImpl {

    private final UserRepositoryPort userRepositoryPort;

    public Mono<UserResponseDto> getById(String id) {
        return userRepositoryPort.findById(id)
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole(),
                        user.getIsActive()
                ));
    }

    public Flux<UserResponseDto> getAll() {
        return userRepositoryPort.findAll()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole(),
                        user.getIsActive()
                ));
    }
}