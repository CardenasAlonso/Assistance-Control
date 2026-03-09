package com.schoolmgmt.infrastructure.adapters.in.web;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.AuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
    private final AuthServicePort authService;

    @PostMapping("/login")
    public Mono<ResponseEntity<TokenResponseDto>> login(@RequestBody LoginCommand cmd) {
        return authService.login(cmd).map(ResponseEntity::ok);
    }

    @GetMapping("/validate")
    public Mono<ResponseEntity<Boolean>> validate(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return authService.validateToken(token).map(ResponseEntity::ok);
    }
}
