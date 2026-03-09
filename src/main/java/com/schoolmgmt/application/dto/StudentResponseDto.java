package com.schoolmgmt.application.dto;

import java.time.LocalDate;

// Sirve para devolver los datos del estudiante al cliente, sin exponer información sensible como la contraseña.
public record StudentResponseDto(
    String id, 
    String sectionId, 
    String userId,
    String firstName, 
    String lastName, 
    LocalDate birthDate, 
    String documentNumber, 
    String email, 
    String phone, 
    String address, 
    String bloodType, 
    String status
) {}