package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("COURSES")
public class CourseEntity {

    @Id
    @Column("id")
    private String id;

    @Column("academic_level_id")
    private String academicLevelId;

    @Column("name")
    private String name;

    @Column("code")
    private String code;

    @Column("description")
    private String description;

    @Column("credits")
    private Integer credits;

    @Column("is_active")
    private Integer isActive;

    @Column("created_at")
    private LocalDateTime createdAt;
}