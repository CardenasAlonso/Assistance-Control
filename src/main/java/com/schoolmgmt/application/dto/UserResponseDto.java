package com.schoolmgmt.application.dto;

public record UserResponseDto(
    String id, 
    String email, 
    String firstName, 
    String lastName, 
    String role, 
    Integer isActive
) {}