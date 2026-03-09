package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private String id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String role;
    private Integer isActive;
    private Integer twoFactorEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}