package com.schoolmgmt.application.dto;

public record UpdateCourseAssignmentCommand(
    String teacherId, 
    String courseId, 
    String sectionId, 
    String academicPeriodId, 
    Integer hoursPerWeek
) {}