package com.schoolmgmt.application.dto;

public record CreateCourseCommand(
    String academicLevelId, // Ej: ID de "Secundaria"
    String name, // Ej: "Álgebra Lineal"
    String code, // Ej: "MAT-101"
    String description,
    Integer credits
) {}