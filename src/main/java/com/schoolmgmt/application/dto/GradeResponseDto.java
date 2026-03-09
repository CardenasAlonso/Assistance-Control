package com.schoolmgmt.application.dto;

public record GradeResponseDto(
    String id, 
    String studentId,
    String courseAssignmentId, 
    String didacticUnitId, 
    Double score, 
    String evaluationType
) {}