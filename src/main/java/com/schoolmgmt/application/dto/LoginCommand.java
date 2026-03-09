package com.schoolmgmt.application.dto;

public record LoginCommand(
    String email, 
    String password
) {}