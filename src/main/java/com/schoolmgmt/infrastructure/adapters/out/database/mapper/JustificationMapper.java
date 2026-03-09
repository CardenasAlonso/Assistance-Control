package com.schoolmgmt.infrastructure.adapters.out.database.mapper;
import com.schoolmgmt.domain.model.Justification;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.JustificationEntity;

public class JustificationMapper {
    private JustificationMapper() {}
    public static Justification toDomain(JustificationEntity e) {
        return Justification.builder().id(e.getId()).attendanceId(e.getAttendanceId())
            .studentId(e.getStudentId()).requestedBy(e.getRequestedBy()).reviewedBy(e.getReviewedBy())
            .reason(e.getReason()).documentUrl(e.getDocumentUrl()).status(e.getStatus())
            .rejectionNote(e.getRejectionNote()).createdAt(e.getCreatedAt()).reviewedAt(e.getReviewedAt()).build();
    }
    public static JustificationEntity toEntity(Justification j) {
        return JustificationEntity.builder().id(j.getId()).attendanceId(j.getAttendanceId())
            .studentId(j.getStudentId()).requestedBy(j.getRequestedBy()).reviewedBy(j.getReviewedBy())
            .reason(j.getReason()).documentUrl(j.getDocumentUrl()).status(j.getStatus())
            .rejectionNote(j.getRejectionNote()).createdAt(j.getCreatedAt()).reviewedAt(j.getReviewedAt()).build();
    }
}
