package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class Teacher {
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;
    private String phone;
    private String specialtiesJson;
    private String status;
    private LocalDate hireDate;
}