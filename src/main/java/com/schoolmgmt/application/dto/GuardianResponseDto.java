package com.schoolmgmt.application.dto;

public record GuardianResponseDto(
    String id, 
    String studentId, 
    String userId, 
    String firstName, 
    String lastName,
    String relationship, 
    String documentNumber, 
    String email, 
    String phone, 
    String address, 
    Integer isPrimaryContact
) {}