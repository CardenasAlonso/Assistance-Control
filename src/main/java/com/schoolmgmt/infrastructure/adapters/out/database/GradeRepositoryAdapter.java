package com.schoolmgmt.infrastructure.adapters.out.database;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.schoolmgmt.application.ports.out.GradeRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.GradeEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class GradeRepositoryAdapter implements GradeRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<GradeEntity> save(GradeEntity gradeEntity) {
        return template.insert(gradeEntity);
    }

    @Override
    public Mono<GradeEntity> update(GradeEntity gradeEntity) {
        return template.update(gradeEntity);
    }

    @Override
    public Mono<GradeEntity> findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.select(GradeEntity.class).matching(query).one();
    }

    @Override
    public Flux<GradeEntity> findAll() {
        return template.select(GradeEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.delete(GradeEntity.class).matching(query).all().then();
    }
}