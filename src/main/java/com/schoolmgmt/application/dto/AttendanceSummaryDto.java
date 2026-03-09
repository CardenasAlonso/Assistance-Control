package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record AttendanceSummaryDto(
    String sectionId, 
    String sectionName, 
    String courseAssignmentId, 
    String courseName,
    LocalDate date, 
    int totalPresent, 
    int totalAbsent, 
    int totalLate, 
    int totalExcused,
    double attendancePercentage
) {}