package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("JUSTIFICATIONS")
public class JustificationEntity {
    
    @Id
    @Column("id")
    private String id;
    
    @Column("attendance_id")
    private String attendanceId;
    
    @Column("student_id")
    private String studentId;
   
    @Column("requested_by")
    private String requestedBy;
   
    @Column("reviewed_by")
    private String reviewedBy;
  
    @Column("reason")
    private String reason;
   
    @Column("document_url")
    private String documentUrl;
  
    @Column("status")
    private String status;
   
    @Column("rejection_note")
    private String rejectionNote;
  
    @Column("created_at")
    private LocalDateTime createdAt;
  
    @Column("reviewed_at")
    private LocalDateTime reviewedAt;
}
