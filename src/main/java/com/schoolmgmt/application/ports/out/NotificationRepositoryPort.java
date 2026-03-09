package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.domain.model.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationRepositoryPort {
    Mono<Notification> save(Notification notification);
    Mono<Notification> findById(String id);
    Flux<Notification> findAll();
    Mono<Void> deleteById(String id);
    Mono<Notification> update(Notification notification);
}
