package com.schoolmgmt.application.ports.out;

import com.schoolmgmt.infrastructure.adapters.out.database.entity.AttendancesEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface AttendanceRepositoryPort {
    Mono<AttendancesEntity> save(AttendancesEntity attendanceEntity);
    Flux<AttendancesEntity> saveAll(Iterable<AttendancesEntity> attendanceEntity); // Para guardar la lista del profe
    Flux<AttendancesEntity> findByStudentIdAndAttendanceDate(String studentId, LocalDate date);
}