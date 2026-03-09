package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Student {
    private String id;
    private String userId;
    private String sectionId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String documentNumber;
    private String email;
    private String phone;
    private String address;
    private String bloodType;
    private String status;
    private LocalDateTime createdAt;
}