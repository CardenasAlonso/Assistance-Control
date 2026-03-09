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
@Table("STUDENT_GRADES")
public class StudentGradesEntity {

    @Id
    @Column("id")
    private String id;

    @Column("student_id")
    private String studentId;

    @Column("course_assignment_id")
    private String courseAssignmentId;

    @Column("didactic_unit_id")
    private String didacticUnitId;

    @Column("score")
    private Double score;

    @Column("evaluation_type")
    private String evaluationType;
}