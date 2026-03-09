package com.schoolmgmt.infrastructure.adapters.out.database;

import com.schoolmgmt.application.ports.out.CourseRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseRepositoryAdapter implements CourseRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<CourseEntity> save(CourseEntity course) {
        return template.insert(course);
    }

    @Override
    public Flux<CourseEntity> findAll() {
        // Trae todos los cursos activos e inactivos
        return template.select(CourseEntity.class).all();
    }

    @Override
    public Mono<CourseEntity> findById(String id) {
        return template.select(CourseEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one();
    }
}