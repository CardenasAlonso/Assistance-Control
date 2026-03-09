package com.schoolmgmt.infrastructure.adapters.out.database;

import com.schoolmgmt.application.ports.out.CourseAssignmentRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.CourseAssignmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseAssignmentRepositoryAdapter implements CourseAssignmentRepositoryPort {
    
    private final R2dbcEntityTemplate template;
    
    @Override 
    public Mono<CourseAssignmentEntity> save(CourseAssignmentEntity assignment) { 
        return template.insert(assignment); 
    }

    @Override 
    public Mono<CourseAssignmentEntity> update(CourseAssignmentEntity entity) { 
        return template.update(entity); 
    }
    
    @Override 
    public Flux<CourseAssignmentEntity> findAll() { 
        return template.select(CourseAssignmentEntity.class).all(); 
    }

    @Override
    public Flux<CourseAssignmentEntity> findByTeacherId(String teacherId) {
        return template.select(CourseAssignmentEntity.class)
                .matching(Query.query(Criteria.where("teacher_id").is(teacherId)))
                .all();
    }

    @Override
    public Mono<CourseAssignmentEntity> findById(String id) {
        return template.select(CourseAssignmentEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one();
    }
}