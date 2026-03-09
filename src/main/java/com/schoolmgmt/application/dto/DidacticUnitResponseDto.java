package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record DidacticUnitResponseDto(
    String id, 
    String courseAssignmentId, 
    Integer unitNumber, 
    String title,
    String description, 
    LocalDate startDate, 
    LocalDate endDate, 
    Integer isActive
) {} 