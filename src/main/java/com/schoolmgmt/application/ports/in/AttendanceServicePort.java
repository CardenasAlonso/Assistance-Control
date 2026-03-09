package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;

public interface AttendanceServicePort {
    Mono<AttendanceResponseDto> recordQrAttendance(RecordQrAttendanceCommand cmd);
    Flux<AttendanceResponseDto> recordManualAttendance(RecordManualAttendanceCommand cmd);
    Flux<AttendanceResponseDto> getAttendanceByDate(LocalDate date);
    Flux<AttendanceResponseDto> getAttendanceByCourseAssignment(String courseAssignmentId);
    Flux<AttendanceResponseDto> getAttendanceByStudentId(String studentId);
    Mono<AttendanceSummaryDto> getAttendanceSummaryBySection(String sectionId, LocalDate date);
}
