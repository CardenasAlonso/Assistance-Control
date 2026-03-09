package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.AuditLogResponseDto;
import com.schoolmgmt.application.dto.CreateAuditLogCommand;
import com.schoolmgmt.application.ports.out.AuditLogRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AuditLogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageAuditLogUseCaseImpl {
    private final AuditLogRepositoryPort auditLogRepositoryPort;

    public Mono<AuditLogResponseDto> logAction(CreateAuditLogCommand command) {
        AuditLogEntity log = AuditLogEntity.builder()
                .id(UUID.randomUUID().toString())
                .userId(command.userId())
                .action(command.action())
                .tableName(command.tableName())
                .oldValues(command.oldValues())
                .newValues(command.newValues())
                .createdAt(LocalDateTime.now())
                .build();
        return auditLogRepositoryPort.save(log).map(this::mapToDto);
    }

    public Flux<AuditLogResponseDto> getAllLogs() { 
        return auditLogRepositoryPort.findAll().map(this::mapToDto); 
    }
    
    public Mono<AuditLogResponseDto> getLogById(String id) { 
        return auditLogRepositoryPort.findById(id).map(this::mapToDto); 
    }

    private AuditLogResponseDto mapToDto(AuditLogEntity a) {
        return new AuditLogResponseDto(
            a.getId(), 
            a.getUserId(), 
            a.getAction(), 
            a.getTableName(), 
            a.getOldValues(), 
            a.getNewValues(), 
            a.getCreatedAt()
        );
    }
}