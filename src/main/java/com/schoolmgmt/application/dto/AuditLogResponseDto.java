package com.schoolmgmt.application.dto;

import java.time.LocalDateTime;

public record AuditLogResponseDto(
    String id, 
    String userId, 
    String action, 
    String tableName, 
    String oldValues, 
    String newValues, 
    LocalDateTime createdAt
) {}