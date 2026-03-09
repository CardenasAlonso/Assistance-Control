package com.schoolmgmt.domain.model;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CourseAssignment {
    private String id;
    private String teacherId;
    private String courseId;
    private String sectionId;
    private String academicPeriodId;
    private Integer hoursPerWeek;
}
