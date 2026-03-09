package com.schoolmgmt.application.dto;

public record RegisterGradeCommand(
    String studentId, 
    String courseAssignmentId, 
    String didacticUnitId, 
    Double score, 
    String evaluationType
) {}