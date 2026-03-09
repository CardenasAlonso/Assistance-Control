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
@Table("ACADEMIC_LEVELS")
public class AcademicLevelEntity {

    @Id
    @Column("id")
    private String id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;
    
    @Column("is_active")
    private Integer isActive;
}