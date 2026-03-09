package com.schoolmgmt.application.dto;

public record CreateAcademicLevelCommand(
    String name,
    String description
) {}