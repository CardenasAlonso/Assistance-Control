package com.schoolmgmt.application.dto;

import java.time.LocalDate;

public record UpdateStudentCommand(
    String sectionId, 
    String firstName, 
    String lastName, 
    LocalDate birthDate, 
    String documentNumber, 
    String email, 
    String phone, 
    String address, 
    String bloodType
) {}