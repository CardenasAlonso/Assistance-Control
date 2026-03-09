package com.schoolmgmt.infrastructure.adapters.out.database;

import com.schoolmgmt.application.ports.out.TeacherRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.TeacherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements TeacherRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<TeacherEntity> save(TeacherEntity teacher) {
        return template.insert(teacher);
    }

    @Override
    public Mono<TeacherEntity> update(TeacherEntity teacher) {
        return template.update(teacher);
    }

    @Override
    public Mono<TeacherEntity> findById(String id) {
        return template.select(TeacherEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one();
    }

    @Override
    public Flux<TeacherEntity> findAll() {
        return template.select(TeacherEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return template.delete(TeacherEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .all()
                .then(); // Retorna Mono<Void> al terminar
    }
}