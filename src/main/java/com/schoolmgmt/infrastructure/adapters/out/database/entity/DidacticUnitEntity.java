package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("DIDACTIC_UNITS")
public class DidacticUnitEntity {
    
    @Id
    @Column("id")
    private String id;
    
    @Column("course_assignment_id")
    private String courseAssignmentId;
    
    @Column("unit_number")
    private Integer unitNumber;
    
    @Column("title")
    private String title;
    
    @Column("description")
    private String description;
    
    @Column("start_date")
    private LocalDate startDate;
   
    @Column("end_date")
    private LocalDate endDate;
    
    @Column("is_active")
    private Integer isActive;
}
