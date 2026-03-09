package com.schoolmgmt.application.usecase;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.AuthServicePort;
import com.schoolmgmt.application.ports.out.UserRepositoryPort;
import com.schoolmgmt.infrastructure.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service @RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthServicePort {
    private final UserRepositoryPort userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<TokenResponseDto> login(LoginCommand cmd) {
        return userRepository.findByEmail(cmd.email())
            .switchIfEmpty(Mono.error(new com.schoolmgmt.domain.exception.ResourceNotFoundException("User", cmd.email())))
            .flatMap(user -> {
                if (!passwordEncoder.matches(cmd.password(), user.getPasswordHash()))
                    return Mono.error(new com.schoolmgmt.domain.exception.DomainException("Credenciales inválidas"));
                if (user.getIsActive() == 0)
                    return Mono.error(new com.schoolmgmt.domain.exception.DomainException("Usuario inactivo"));
                String token = jwtUtil.generateToken(user.getId(), user.getRole());
                return Mono.just(new TokenResponseDto(token, token, user.getId(), user.getRole(), 86400000L));
            });
    }

    @Override
    public Mono<Boolean> validateToken(String token) {
        return Mono.just(jwtUtil.isTokenValid(token));
    }

    @Override
    public Mono<String> extractUserId(String token) {
        return Mono.just(jwtUtil.extractUserId(token));
    }

    @Override
    public Mono<String> extractRole(String token) {
        return Mono.just(jwtUtil.extractRole(token));
    }
}
