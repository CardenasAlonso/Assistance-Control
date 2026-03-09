package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AuditLogEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuditLogRepositoryPort {
    Mono<AuditLogEntity> save(AuditLogEntity log);
    Mono<AuditLogEntity> findById(String id);
    Flux<AuditLogEntity> findAll();
}