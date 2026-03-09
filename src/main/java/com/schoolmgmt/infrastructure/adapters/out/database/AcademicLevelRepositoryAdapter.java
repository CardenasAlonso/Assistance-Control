package com.schoolmgmt.infrastructure.adapters.out.database;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.schoolmgmt.application.ports.out.AcademicLevelRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicLevelEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AcademicLevelRepositoryAdapter implements AcademicLevelRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<AcademicLevelEntity> save(AcademicLevelEntity entity) {
        return template.insert(entity);
    }

    @Override
    public Mono<AcademicLevelEntity> update(AcademicLevelEntity entity) {
        return template.update(entity);
    }

    @Override
    public Mono<AcademicLevelEntity> findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.select(AcademicLevelEntity.class).matching(query).one();
    }

    @Override
    public Flux<AcademicLevelEntity> findAll() {
        return template.select(AcademicLevelEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.delete(AcademicLevelEntity.class).matching(query).all().then();
    }
}