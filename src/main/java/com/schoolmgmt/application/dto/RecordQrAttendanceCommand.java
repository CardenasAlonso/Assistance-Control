package com.schoolmgmt.application.dto;

public record RecordQrAttendanceCommand(
        String studentId // El QR solo lee el ID del estudiante
) {}