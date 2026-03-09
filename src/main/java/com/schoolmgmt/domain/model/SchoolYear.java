package com.schoolmgmt.domain.model;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class SchoolYear {
    private String id;
    private String academicLevelId;
    private String name;
    private Integer gradeNumber;
    private Integer isActive;
}
