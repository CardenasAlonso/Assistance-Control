package com.schoolmgmt.application.dto;

import java.time.LocalDateTime;

public record JustificationResponseDto(
    String id, 
    String attendanceId, 
    String studentId, 
    String requestedBy,
    String reviewedBy, 
    String reason, 
    String status, 
    String rejectionNote,
    LocalDateTime createdAt, 
    LocalDateTime reviewedAt
) {}