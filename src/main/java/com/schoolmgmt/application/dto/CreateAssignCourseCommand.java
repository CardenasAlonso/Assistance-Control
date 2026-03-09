package com.schoolmgmt.application.dto;

public record CreateAssignCourseCommand(
    String teacherId, // FK a TEACHERS
    String courseId,  // FK a COURSES
    String sectionId, // FK a SECTIONS
    String academicPeriodId,
    Integer hoursPerWeek
) {}