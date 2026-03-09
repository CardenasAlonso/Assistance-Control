package com.schoolmgmt.application.dto;

public record CreateAuditLogCommand(
    String userId, 
    String action, 
    String tableName, 
    String oldValues, 
    String newValues
) {}