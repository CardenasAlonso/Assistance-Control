package com.schoolmgmt.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentGrades {
    private String id;
    private String studentId;
    private String courseAssigmentId;
    private String didacticUnitId;
    private Integer score;
    private String evaluationType;
}