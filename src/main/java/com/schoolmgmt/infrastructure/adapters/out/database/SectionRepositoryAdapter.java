package com.schoolmgmt.infrastructure.adapters.out.database;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.schoolmgmt.application.ports.out.SectionRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.SectionEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SectionRepositoryAdapter implements SectionRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<SectionEntity> save(SectionEntity sectionEntity) {
        return template.insert(sectionEntity);
    }

    @Override
    public Mono<SectionEntity> update(SectionEntity sectionEntity) {
        return template.update(sectionEntity);
    }

    @Override
    public Mono<SectionEntity> findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.select(SectionEntity.class).matching(query).one();
    }

    @Override
    public Flux<SectionEntity> findAll() {
        return template.select(SectionEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.delete(SectionEntity.class).matching(query).all().then();
    }
}