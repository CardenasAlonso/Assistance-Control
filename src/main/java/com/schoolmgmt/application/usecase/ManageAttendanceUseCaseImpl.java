package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.AttendanceResponseDto;
import com.schoolmgmt.application.dto.RecordManualAttendanceCommand;
import com.schoolmgmt.application.dto.RecordQrAttendanceCommand;
import com.schoolmgmt.application.ports.out.AttendanceRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AttendancesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManageAttendanceUseCaseImpl {

    private final AttendanceRepositoryPort attendanceRepositoryPort;

    // 1. FLUJO QR BLINDADO CONTRA ABUSOS
    public Mono<AttendanceResponseDto> recordQrAttendance(RecordQrAttendanceCommand command) {
        LocalDate today = LocalDate.now();

        // 1. Buscar si ya existe una asistencia HOY
        return attendanceRepositoryPort.findByStudentIdAndAttendanceDate(command.studentId(), today)
                // 2. Filtramos solo las de "entrada general" (puerta)
                .filter(att -> att.getCourseAssignmentId() == null)
                .hasElements() // Devuelve un Mono<Boolean> (true si ya existe, false si no)
                .flatMap(yaIngreso -> {
                    if (yaIngreso) {
                        // REGLA 1: Evitar el Doble Escaneo (Spam)
                        return Mono.error(new IllegalArgumentException("El estudiante ya registró su entrada hoy. No se permiten escaneos duplicados."));
                    }

                    // Si no ha ingresado, procedemos a registrarlo
                    LocalTime now = LocalTime.now();
                    LocalTime limiteTardanza = LocalTime.of(8, 0);
                    String status = now.isAfter(limiteTardanza) ? "LATE" : "PRESENT";

                    AttendancesEntity attendance = AttendancesEntity.builder()
                            .id(UUID.randomUUID().toString())
                            .studentId(command.studentId())
                            .courseAssignmentId(null)
                            .attendanceDate(today)
                            .checkInTime(now)
                            .status(status)
                            .recordMethod("QR")
                            .build();

                    return attendanceRepositoryPort.save(attendance);
                })
                // REGLA 2: Capturar IDs Falsos (Si la BD rechaza el studentId por no existir)
                .onErrorResume(DataIntegrityViolationException.class, e -> 
                        Mono.error(new IllegalArgumentException("El ID del estudiante no es válido o no existe en el sistema.")))
                .map(this::mapToDto);
    }

    // 2. FLUJO MANUAL (Por ahora lo dejamos igual)
    public Flux<AttendanceResponseDto> recordManualAttendance(RecordManualAttendanceCommand command) {
        
        List<AttendancesEntity> entities = command.attendances().stream()
                .map(item -> AttendancesEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .studentId(item.studentId())
                        .courseAssignmentId(command.courseAssignmentId()) 
                        .attendanceDate(LocalDate.now())
                        .checkInTime(LocalTime.now())
                        .status(item.status())
                        .recordMethod("MANUAL")
                        .justificationReason(item.justificationReason())
                        .build())
                .collect(Collectors.toList());

        return attendanceRepositoryPort.saveAll(entities)
                .map(this::mapToDto);
    }

    private AttendanceResponseDto mapToDto(AttendancesEntity entity) {
        return new AttendanceResponseDto(
                entity.getId(),
                entity.getStudentId(),
                entity.getCourseAssignmentId(),
                entity.getAttendanceDate(),
                entity.getCheckInTime(),
                entity.getStatus(),
                entity.getRecordMethod(),
                entity.getJustificationReason()
        );
    }
}