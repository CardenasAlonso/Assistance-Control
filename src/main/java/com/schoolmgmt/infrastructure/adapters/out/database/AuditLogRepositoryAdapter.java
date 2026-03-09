package com.schoolmgmt.infrastructure.adapters.out.database;

import com.schoolmgmt.application.ports.out.AuditLogRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AuditLogEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuditLogRepositoryAdapter implements AuditLogRepositoryPort {

    private final R2dbcEntityTemplate template;
    
    @Override public Mono<AuditLogEntity> save(AuditLogEntity log) { 
        return template.insert(log); 
    }

    @Override public Mono<AuditLogEntity> findById(String id) { 
        return template.select(AuditLogEntity.class).matching(Query.query(Criteria.where("id").is(id))).one(); 
    }

    @Override public Flux<AuditLogEntity> findAll() { 
        return template.select(AuditLogEntity.class).all(); 
    }

    @Override
    public Mono<AuditLogEntity> saveLog(String action, String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveLog'");
    }

    @Override
    public Flux<AuditLogEntity> getAllLogs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllLogs'");
    }
}