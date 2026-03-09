package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("STUDENTS")
public class StudentEntity {
    @Id
    @Column("id")
    private String id;

    @Column("user_id")
    private String userId;

    @Column("section_id")
    private String sectionId;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("birth_date")
    private LocalDate birthDate;

    @Column("document_number")
    private String documentNumber;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("address")
    private String address;

    @Column("blood_type")
    private String bloodType;

    @Column("status")
    private String status;

    @Column("created_at")
    private LocalDateTime createdAt;
}