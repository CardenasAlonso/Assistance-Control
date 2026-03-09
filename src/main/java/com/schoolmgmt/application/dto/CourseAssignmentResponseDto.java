package com.schoolmgmt.application.dto;

public record CourseAssignmentResponseDto(
    String id, 
    String teacherId, 
    String courseId, 
    String sectionId, 
    String academicPeriodId, 
    Integer hoursPerWeek
) {}