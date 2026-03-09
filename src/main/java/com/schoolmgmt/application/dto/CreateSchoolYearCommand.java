package com.schoolmgmt.application.dto;

public record CreateSchoolYearCommand(
    String academicLevelId, 
    String name, 
    Integer gradeNumber
) {}