package com.schoolmgmt.infrastructure.adapters.out.database;

import com.schoolmgmt.application.ports.out.AcademicPeriodRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AcademicPeriodEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AcademicPeriodRepositoryAdapter implements AcademicPeriodRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<AcademicPeriodEntity> save(AcademicPeriodEntity period) {
        return template.insert(period);
    }

    @Override
    public Mono<AcademicPeriodEntity> update(AcademicPeriodEntity period) {
        return template.update(period);
    }

    @Override
    public Mono<AcademicPeriodEntity> findById(String id) {
        return template.select(AcademicPeriodEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one();
    }

    @Override
    public Flux<AcademicPeriodEntity> findAll() {
        return template.select(AcademicPeriodEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return template.delete(AcademicPeriodEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .all()
                .then();
    }
}