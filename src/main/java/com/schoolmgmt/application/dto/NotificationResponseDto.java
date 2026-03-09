package com.schoolmgmt.application.dto;

import java.time.LocalDateTime;

public record NotificationResponseDto(
    String id, 
    String recipientId, 
    String type, 
    String title, 
    String body,
    String referenceId, 
    String referenceType, 
    Integer isRead, 
    LocalDateTime createdAt
) {}