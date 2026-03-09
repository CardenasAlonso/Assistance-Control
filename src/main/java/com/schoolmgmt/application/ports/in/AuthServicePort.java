package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Mono;

public interface AuthServicePort {
    Mono<TokenResponseDto> login(LoginCommand cmd);
    Mono<Boolean> validateToken(String token);
    Mono<String> extractUserId(String token);
    Mono<String> extractRole(String token);
}
