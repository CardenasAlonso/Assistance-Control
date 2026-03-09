package com.schoolmgmt.application.dto;

public record RegisterScoreCommand(
    String studentId, 
    String courseAssignmentId, 
    String didacticUnitId,
    Double score, 
    String evaluationType, 
    String comments
) {}