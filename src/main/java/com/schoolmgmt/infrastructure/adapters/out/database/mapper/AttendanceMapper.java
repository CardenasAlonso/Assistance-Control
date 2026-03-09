package com.schoolmgmt.infrastructure.adapters.out.database.mapper;
import com.schoolmgmt.domain.model.Attendance;
import com.schoolmgmt.domain.enums.AttendanceStatus;
import com.schoolmgmt.domain.enums.RecordMethod;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AttendancesEntity;

public class AttendanceMapper {
    private AttendanceMapper() {}

    public static Attendance toDomain(AttendancesEntity e) {
        return Attendance.builder()
            .id(e.getId()).studentId(e.getStudentId())
            .courseAssignmentId(e.getCourseAssignmentId())
            .attendanceDate(e.getAttendanceDate())
            .checkInTime(e.getCheckInTime())
            .status(e.getStatus() != null ? AttendanceStatus.valueOf(e.getStatus()) : null)
            .justificationReason(e.getJustificationReason())
            .recordMethod(e.getRecordMethod() != null ? RecordMethod.valueOf(e.getRecordMethod()) : RecordMethod.MANUAL)
            .build();
    }

    public static AttendancesEntity toEntity(Attendance a) {
        return AttendancesEntity.builder()
            .id(a.getId()).studentId(a.getStudentId())
            .courseAssignmentId(a.getCourseAssignmentId())
            .attendanceDate(a.getAttendanceDate())
            .checkInTime(a.getCheckInTime())
            .status(a.getStatus() != null ? a.getStatus().name() : null)
            .justificationReason(a.getJustificationReason())
            .recordMethod(a.getRecordMethod() != null ? a.getRecordMethod().name() : "MANUAL")
            .build();
    }
}
