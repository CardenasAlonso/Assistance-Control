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
                .filter(att -> "QR_DOOR".equals(att.recordMethod()))
                // 3. Si ya hay una asistencia de entrada general, no permitimos registrar otra
                .hasElements()
                .flatMap(hasEntry -> {
                    if (hasEntry) {
                        return Mono.error(new DataIntegrityViolationException("Ya se ha registrado una asistencia de entrada general para este estudiante hoy."));
                    } else {
                        // Si no hay una asistencia de entrada general, permitimos registrar la nueva asistencia
                        AttendancesEntity newAttendance = AttendancesEntity.builder()
                                .id(UUID.randomUUID().toString())
                                .studentId(command.studentId())
                                .courseAssignmentId(null) // null = QR door
                                .attendanceDate(today)
                                .checkInTime(LocalTime.now())
                                .status("PRESENTE")
                                .recordMethod("QR_DOOR")
                                .justificationReason(null)
                                .build();

                        return attendanceRepositoryPort.save(newAttendance)
                                .map(this::mapToDto);
                    }
                });
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