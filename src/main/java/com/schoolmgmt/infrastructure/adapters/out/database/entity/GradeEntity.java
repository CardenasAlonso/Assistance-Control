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
@Table("GRADES")
public class GradeEntity {

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