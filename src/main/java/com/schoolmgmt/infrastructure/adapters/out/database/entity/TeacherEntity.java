package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("TEACHERS")
public class TeacherEntity {

    @Id
    @Column("id")
    private String id;

    @Column("user_id")
    private String userId;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("document_number")
    private String documentNumber;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("specialties_json")
    private String specialtiesJson;

    @Column("status")
    private String status;

    @Column("hire_date")
    private LocalDate hireDate;
}