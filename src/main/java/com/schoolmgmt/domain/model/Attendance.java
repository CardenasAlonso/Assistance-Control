package com.schoolmgmt.domain.model;
import com.schoolmgmt.domain.enums.AttendanceStatus;
import com.schoolmgmt.domain.enums.RecordMethod;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data @Builder
public class Attendance {
    private String id;
    private String studentId;
    private String courseAssignmentId; // null = QR entrada al colegio
    private LocalDate attendanceDate;
    private LocalTime checkInTime;
    private AttendanceStatus status;
    private String justificationReason;
    private RecordMethod recordMethod;
}
