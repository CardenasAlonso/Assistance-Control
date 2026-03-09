package com.schoolmgmt.domain.model;
import com.schoolmgmt.domain.enums.NotificationType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class Notification {
    private String id;
    private String recipientId;
    private String senderId;
    private NotificationType type;
    private String title;
    private String body;
    private String referenceId;
    private String referenceType;
    private Integer isRead;
    private LocalDateTime createdAt;
}
