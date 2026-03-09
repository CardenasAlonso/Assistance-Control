package com.schoolmgmt.application.dto;

public record RequestJustificationCommand(
    String attendanceId, 
    String studentId, 
    String requestedBy,
    String reason, 
    String documentUrl
) {}