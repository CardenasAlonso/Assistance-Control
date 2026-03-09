package com.schoolmgmt.application.dto;

import java.time.Instant;

public record BlockResponseDto(
    String id, 
    Integer blockIndex, 
    String eventType, 
    String entityId,
    String entityType, 
    String payload, 
    String previousHash, 
    String hash,
    String createdBy, 
    Instant timestamp, 
    boolean valid
) {}