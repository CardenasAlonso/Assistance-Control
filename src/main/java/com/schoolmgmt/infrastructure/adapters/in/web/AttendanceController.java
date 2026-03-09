package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.AttendanceResponseDto;
import com.schoolmgmt.application.dto.RecordManualAttendanceCommand;
import com.schoolmgmt.application.dto.RecordQrAttendanceCommand;
import com.schoolmgmt.application.usecase.ManageAttendanceUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map; // Para el map de error

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final ManageAttendanceUseCaseImpl useCase;

    @PostMapping("/qr")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AttendanceResponseDto> recordQr(@RequestBody RecordQrAttendanceCommand command) {
        return useCase.recordQrAttendance(command);
    }

    @PostMapping("/manual")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<AttendanceResponseDto> recordManualBatch(@RequestBody RecordManualAttendanceCommand command) {
        return useCase.recordManualAttendance(command);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleIllegalArgumentException(IllegalArgumentException ex) {
        // En lugar de un 500 y texto plano, devolvemos un 400 con un JSON estructurado
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "Bad Request",
                        "message", ex.getMessage() // Aquí sale tu mensaje: "El estudiante ya registró..."
                )));
    }
}