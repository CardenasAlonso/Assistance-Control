package com.schoolmgmt.application.dto;

public record CreateGradeCommand(
    String academicLevelId,
    String name,
    Integer gradeNumber
) {}