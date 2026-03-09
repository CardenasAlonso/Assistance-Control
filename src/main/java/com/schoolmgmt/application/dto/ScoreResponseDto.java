package com.schoolmgmt.application.dto;

import java.time.LocalDateTime;

public record ScoreResponseDto(
    String id, 
    String studentId, 
    String courseAssignmentId, 
    String didacticUnitId,
    Double score, 
    String evaluationType, 
    String performanceLevel, 
    boolean approved,
    String comments, 
    LocalDateTime registeredAt
) {}