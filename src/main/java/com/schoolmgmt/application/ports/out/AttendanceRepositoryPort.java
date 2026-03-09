package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.application.dto.AttendanceResponseDto;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AttendancesEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;

public interface AttendanceRepositoryPort {
    Mono<AttendanceResponseDto> save(AttendancesEntity attendance);
    Mono<AttendanceResponseDto> findById(String id);
    Flux<AttendanceResponseDto> findAll();
    Flux<AttendanceResponseDto> findByStudentIdAndAttendanceDate(String studentId, LocalDate attendanceDate);
}
