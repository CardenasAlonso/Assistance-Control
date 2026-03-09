package com.schoolmgmt.domain.model;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class Justification {
    private String id;
    private String attendanceId;
    private String studentId;
    private String requestedBy;
    private String reviewedBy;
    private String reason;
    private String documentUrl;
    private String status; // PENDING | APPROVED | REJECTED
    private String rejectionNote;
    private LocalDateTime createdAt;
    private LocalDateTime reviewedAt;
}
