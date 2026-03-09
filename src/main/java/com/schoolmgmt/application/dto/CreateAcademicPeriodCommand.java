package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record CreateAcademicPeriodCommand(
    String name, 
    LocalDate startDate, 
    LocalDate endDate
) {}