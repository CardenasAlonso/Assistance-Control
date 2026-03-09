package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record UpdateTeacherCommand(
    String firstName,
    String lastName,
    String documentNumber,
    String email,
    String phone,
    String specialtiesJson,
    LocalDate hireDate
) {}