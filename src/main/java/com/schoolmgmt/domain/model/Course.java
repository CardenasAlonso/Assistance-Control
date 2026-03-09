package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Course {
    private String id;
    private String academicLevelId;
    private String name;
    private String code;
    private String description;
    private Integer credits;
    private Integer isActive;
    private LocalDateTime createdAt;
}