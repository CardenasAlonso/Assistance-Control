package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record RegisterStudentCommand(

    // Datos para la cuenta (User)
    String email,
    String password,

    // Datos para el perfil (Student)
    String firstName,
    String lastName,
    String documentNumber,
    String sectionId,
    LocalDate birthDate,
    String phone,
    String address,
    String bloodType
) {}