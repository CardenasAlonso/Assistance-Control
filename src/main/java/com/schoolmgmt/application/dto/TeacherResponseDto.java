package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record TeacherResponseDto(
    String id,
    String firstName,
    String lastName,
    String documentNumber, 
    String email,
    String phone,
    String specialtiesJson,
    String status,
    LocalDate hireDate
) {}