package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("NOTIFICATIONS")
public class NotificationEntity {
    
    @Id
    @Column("id")
    private String id;
   
    @Column("recipient_id")
    private String recipientId;
   
    @Column("sender_id")
    private String senderId;
   
    @Column("type")
    private String type;
   
    @Column("title")
    private String title;
   
    @Column("body")
    private String body;
   
    @Column("reference_id")
    private String referenceId;
  
    @Column("reference_type")
    private String referenceType;
   
    @Column("is_read")
    private Integer isRead;
   
    @Column("created_at")
    private LocalDateTime createdAt;
}
