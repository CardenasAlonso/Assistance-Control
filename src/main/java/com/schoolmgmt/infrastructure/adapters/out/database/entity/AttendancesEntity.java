package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private String courseAssignmentId; // nullable - null = QR door
    
    @Column("attendance_date")
    private LocalDate attendanceDate;
    
    @Column("check_in_time")
    private LocalTime checkInTime;
    
    @Column("status")
    private String status;
    
    @Column("justification_reason")
    private String justificationReason;
    
    @Column("record_method")
    private String recordMethod;
    
}
