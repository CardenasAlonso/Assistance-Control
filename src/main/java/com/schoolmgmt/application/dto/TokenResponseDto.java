package com.schoolmgmt.application.dto;

public record TokenResponseDto(
    String token, 
    String refreshToken, 
    String userId, 
    String role, 
    long expiresIn
) {}