package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageNotificationUseCase {
    Mono<NotificationResponseDto> sendNotification(NotificationResponseDto dto);
    Flux<NotificationResponseDto> getByRecipientId(String recipientId);
    Flux<NotificationResponseDto> getUnreadByRecipient(String recipientId);
    Mono<Void> markAsRead(String id);
    Mono<Void> markAllAsRead(String recipientId);
    Mono<Integer> generateAbsenceAlerts();
}
