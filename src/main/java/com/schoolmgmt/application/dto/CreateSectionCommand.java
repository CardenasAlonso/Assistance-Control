package com.schoolmgmt.application.dto;

public record CreateSectionCommand(
    String gradeId, // El ID del grado (ej. 1er año) al que pertenece
    String name,    // Ej: "A", "B", "Única"
    Integer capacity,
    String turn     // Ej: "MAÑANA", "TARDE"
) {}