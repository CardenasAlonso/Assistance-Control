package com.schoolmgmt.application.dto;

public record AcademicLevelResponseDto(
    String id,
    String name,
    String description,
    Integer isActive
) {}