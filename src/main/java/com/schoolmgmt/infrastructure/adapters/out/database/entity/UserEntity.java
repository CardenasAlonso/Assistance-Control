package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data // Genera Getters, Setters y toString automáticamente
@Builder // Permite usar UserEntity.builder()...
@NoArgsConstructor // Genera el constructor vacío (obligatorio para Spring Data)
@AllArgsConstructor // Genera el constructor con todos los argumentos (necesario para @Builder)
@Table("USERS")
public class UserEntity {

    @Id
    @Column("id")
    private String id; // Es VARCHAR2(50), por lo que es String, NO Long.

    @Column("email")
    private String email;

    @Column("password_hash")
    private String passwordHash;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("role")
    private String role;

    @Column("is_active")
    private Integer isActive;

    @Column("two_factor_enabled")
    private Integer twoFactorEnabled; 

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}