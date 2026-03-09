package com.schoolmgmt.application.dto;

public record UpdateScoreCommand(
    Double score, 
    String evaluationType, 
    String comments
) {}