package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Guardians {
    private String id;
    private String studentId;
    private String userId;
    private String firstName;
    private String lastName;
    private String relationship;
    private String documentNumber;
    private String phone;
    private String email;
    private String address;
    private Integer isPrimaryContact;
}