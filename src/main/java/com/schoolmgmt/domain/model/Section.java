package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Section {
    private String id;
    private String gradeId;
    private String name;
    private Integer capacity;
    private String turn;
    private Integer isActive;
}