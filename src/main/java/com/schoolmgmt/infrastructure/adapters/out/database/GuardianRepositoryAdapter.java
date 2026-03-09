package com.schoolmgmt.infrastructure.adapters.out.database;

import com.schoolmgmt.application.ports.out.GuardianRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.GuardianEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GuardianRepositoryAdapter implements GuardianRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<GuardianEntity> save(GuardianEntity guardian) {
        return template.insert(guardian);
    }

    @Override
    public Mono<GuardianEntity> update(GuardianEntity guardian) {
        return template.update(guardian);
    }

    @Override
    public Mono<GuardianEntity> findById(String id) {
        return template.select(GuardianEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one();
    }

    @Override
    public Flux<GuardianEntity> findAll() {
        return template.select(GuardianEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return template.delete(GuardianEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .all().then();
    }
}