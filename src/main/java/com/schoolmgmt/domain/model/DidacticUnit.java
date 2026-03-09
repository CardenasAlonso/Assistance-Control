package com.schoolmgmt.domain.model;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data @Builder
public class DidacticUnit {
    private String id;
    private String courseAssignmentId;
    private Integer unitNumber;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer isActive;
}
