package com.schoolmgmt.application.dto;

import java.util.List;

public record RecordManualAttendanceCommand(
        String courseAssignmentId, // Puede ser null si es el auxiliar en la puerta
        List<StudentAttendanceItem> attendances
) {
    public record StudentAttendanceItem(
            String studentId,
            String status, // 'PRESENT', 'ABSENT', 'LATE', 'EXCUSED'
            String justificationReason // Ej: "Se fue al hospital"
    ) {}
}