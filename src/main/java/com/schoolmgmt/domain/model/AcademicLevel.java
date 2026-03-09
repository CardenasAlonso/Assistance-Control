package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcademicLevel {
    private String id;
    private String name;
    private String description;
    private Integer isActive;
}