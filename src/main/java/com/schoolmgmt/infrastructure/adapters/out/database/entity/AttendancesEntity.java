package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("ATTENDANCES")
public class AttendancesEntity {

    @Id
    @Column("id")
    private String id;

    @Column("student_id")
    private String studentId;

    @Column("course_assignment_id")
    private String courseAssignmentId;

    @Column("attendance_date")
    private LocalDate attendanceDate;

    @Column("status")
    private String status;

    @Column("justification_reason")
    private String justificationReason;

    @Column("check_in_time")
    private LocalTime checkInTime;

    @Column("record_method")
    private String recordMethod;
}