package com.schoolmgmt.application.dto;

public record SchoolYearResponseDto(
    String id, 
    String academicLevelId, 
    String name, 
    Integer gradeNumber, 
    Integer isActive
) {}