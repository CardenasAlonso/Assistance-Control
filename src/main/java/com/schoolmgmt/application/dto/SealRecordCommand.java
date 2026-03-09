package com.schoolmgmt.application.dto;

public record SealRecordCommand(
    String eventType, 
    String entityId, 
    String entityType,
    String payload, 
    String createdBy
) {}