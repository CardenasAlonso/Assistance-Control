package com.schoolmgmt.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attendances {
    private String id;
    private String studentId;
    private String courseAssignmentId;
    private LocalDate attendanceDate;
    private String status;
    private String justificationReason;
    private LocalTime checkInTime;
    private String recordMethod;
}