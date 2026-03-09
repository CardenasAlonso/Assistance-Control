package com.schoolmgmt.application.dto;

public record UpdateGuardianCommand(
    String firstName, 
    String lastName, 
    String relationship, 
    String documentNumber, 
    String phone, 
    String address, 
    Integer isPrimaryContact
) {}