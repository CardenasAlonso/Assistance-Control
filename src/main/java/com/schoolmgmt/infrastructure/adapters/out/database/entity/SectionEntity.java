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
@Table("SECTIONS")
public class SectionEntity {

    @Id
    @Column("id")
    private String id;

    @Column("grade_id")
    private String gradeId; // FK añadida

    @Column("name")
    private String name;

    @Column("capacity")
    private Integer capacity;

    @Column("turn")
    private String turn;

    @Column("is_active")
    private Integer isActive; // Mapea a NUMBER(1)
}