package com.schoolmgmt.application.dto;

import java.time.LocalDateTime;

public record CourseResponseDto(
    String id,
    String academicLevelId,
    String name,
    String code,
    String description,
    Integer credits,
    Integer isActive,
    LocalDateTime createdAt
) {}