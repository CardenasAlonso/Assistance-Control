package com.schoolmgmt.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcademicPeriod {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer isActive;
    private LocalDateTime createdAt;
}