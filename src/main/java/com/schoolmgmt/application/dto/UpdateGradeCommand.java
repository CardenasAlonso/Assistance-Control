package com.schoolmgmt.application.dto;

public record UpdateGradeCommand(
    Double score, 
    String evaluationType
) {}