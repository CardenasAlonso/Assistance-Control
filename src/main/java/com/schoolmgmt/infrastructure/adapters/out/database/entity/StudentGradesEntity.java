package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("STUDENT_SCORES")
public class StudentGradesEntity {
   
    @Id
    @Column("id")
    private String id;
   
    @Column("student_id")
    private String studentId;
   
    @Column("course_assignment_id")
    private String courseAssignmentId; // fixed typo
   
    @Column("didactic_unit_id")
    private String didacticUnitId;
   
    @Column("score")
    private Double score;
   
    @Column("evaluation_type")
    private String evaluationType;
   
    @Column("comments")
    private String comments;
   
    @Column("registered_at")
    private LocalDateTime registeredAt;
}
