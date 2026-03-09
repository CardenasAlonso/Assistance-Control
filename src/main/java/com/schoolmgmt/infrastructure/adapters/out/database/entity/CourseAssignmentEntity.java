package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("COURSE_ASSIGNMENTS")
public class CourseAssignmentEntity {

    @Id
    @Column("id")
    private String id;

    @Column("teacher_id")
    private String teacherId;

    @Column("course_id")
    private String courseId;

    @Column("section_id")
    private String sectionId;

    @Column("academic_period_id")
    private String academicPeriodId;

    @Column("hours_per_week")
    private Integer hoursPerWeek;
}