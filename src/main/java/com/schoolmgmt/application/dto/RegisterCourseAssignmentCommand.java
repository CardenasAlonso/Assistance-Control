package com.schoolmgmt.application.dto;

public record RegisterCourseAssignmentCommand(
    String teacherId, 
    String courseId, 
    String sectionId, 
    String academicPeriodId, 
    Integer hoursPerWeek
) {}