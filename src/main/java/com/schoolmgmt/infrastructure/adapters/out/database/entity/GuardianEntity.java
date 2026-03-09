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
@Table("GUARDIANS")
public class GuardianEntity {

    @Id
    @Column("id")
    private String id;

    @Column("student_id")
    private String studentId;

    @Column("user_id")
    private String userId;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("relationship")
    private String relationship;

    @Column("document_number")
    private String documentNumber;

    @Column("phone")
    private String phone;

    @Column("email")
    private String email;

    @Column("address")
    private String address;

    @Column("is_primary_contact")
    private Integer isPrimaryContact; // 1 = Sí, 0 = No
}