package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record RegisterTeacherCommand(
    
    // Datos para la cuenta (User)
    String email,
    String password,
    
    // Datos para el perfil (Teacher)
    String firstName,
    String lastName,
    String documentNumber,
    String phone,
    String specialtiesJson,
    LocalDate hireDate
) {}