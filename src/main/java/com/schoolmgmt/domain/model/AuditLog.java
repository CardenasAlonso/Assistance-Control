package com.schoolmgmt.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditLog {
    private String id;
    private String userId;
    private String action;
    private String tableName;
    private String oldValues;
    private String newValues;
    private LocalDateTime createdAt;
}