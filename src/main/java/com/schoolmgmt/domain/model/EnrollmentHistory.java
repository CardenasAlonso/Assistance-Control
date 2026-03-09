package com.schoolmgmt.domain.model;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data @Builder
public class EnrollmentHistory {
    private String id;
    private String studentId;
    private String sectionId;
    private String academicPeriodId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String registeredBy;
}
