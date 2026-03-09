package com.schoolmgmt.infrastructure.config;
import com.schoolmgmt.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleNotFound(ResourceNotFoundException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(error(HttpStatus.NOT_FOUND.value(), ex.getMessage())));
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleDuplicate(DuplicateRecordException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
            .body(error(HttpStatus.CONFLICT.value(), ex.getMessage())));
    }

    @ExceptionHandler(InvalidScoreException.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleInvalidScore(InvalidScoreException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(error(HttpStatus.BAD_REQUEST.value(), ex.getMessage())));
    }

    @ExceptionHandler(BlockchainIntegrityException.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleBlockchain(BlockchainIntegrityException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage())));
    }

    @ExceptionHandler(DomainException.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleDomain(DomainException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(error(422, ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleGeneric(Exception ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error(500, "Error interno del servidor")));
    }

    private Map<String,Object> error(int status, String message) {
        return Map.of("status", status, "message", message, "timestamp", LocalDateTime.now().toString());
    }
}
