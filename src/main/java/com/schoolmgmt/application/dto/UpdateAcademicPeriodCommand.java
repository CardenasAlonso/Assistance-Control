package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record UpdateAcademicPeriodCommand(
    String name, 
    LocalDate startDate, 
    LocalDate endDate
) {}