package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("AUDIT_LOGS")
public class AuditLogEntity {

    @Id
    @Column("id")
    private String id;

    @Column("user_id")
    private String userId;

    @Column("action")
    private String action;

    @Column("table_name")
    private String tableName;

    @Column("old_values")
    private String oldValues;

    @Column("new_values")
    private String newValues;

    @Column("created_at")
    private LocalDateTime createdAt;
}