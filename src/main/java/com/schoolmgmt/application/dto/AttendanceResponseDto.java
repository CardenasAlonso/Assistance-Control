package com.schoolmgmt.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AttendanceResponseDto(
    String id,
    String studentId,
    String courseAssignmentId,
    LocalDate attendanceDate,
    LocalTime checkInTime,
    String status,
    String recordMethod,
    String justificationReason
) {}