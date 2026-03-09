package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("SCHOOL_YEARS")
public class SchoolYearEntity {
   
    @Id
    @Column("id")
    private String id;
   
    @Column("academic_level_id")
    private String academicLevelId;
   
    @Column("name")
    private String name;
   
    @Column("grade_number")
    private Integer gradeNumber;
   
    @Column("is_active")
    private Integer isActive;
}
