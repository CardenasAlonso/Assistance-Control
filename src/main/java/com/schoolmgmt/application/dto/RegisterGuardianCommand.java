package com.schoolmgmt.application.dto;

public record RegisterGuardianCommand(
    String email, 
    String password, // Para crear la cuenta de usuario
    String studentId, // Para vincularlo a su hijo
    String firstName,
    String lastName, 
    String relationship, 
    String documentNumber, 
    String phone, 
    String address, 
    Integer isPrimaryContact
) {}