package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record CreateDidacticUnitCommand(
    String courseAssignmentId, 
    Integer unitNumber, 
    String title,
    String description, 
    LocalDate startDate, 
    LocalDate endDate
) {}