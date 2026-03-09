package com.schoolmgmt.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AcademicPeriodResponseDto(
    String id, 
    String name, 
    LocalDate startDate, 
    LocalDate endDate,
    Integer isActive, 
    LocalDateTime createdAt
) {}