package com.schoolmgmt.infrastructure.adapters.out.database;
import com.schoolmgmt.application.ports.out.StudentGradesRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentGradesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentGradesRepositoryAdapter implements StudentGradesRepositoryPort {
    private final R2dbcEntityTemplate template;

    @Override public Mono<StudentGradesEntity> save(StudentGradesEntity grade) { 
        return template.insert(grade); 
    }

    @Override public Mono<StudentGradesEntity> update(StudentGradesEntity grade) { 
        return template.update(grade); 
    }

    @Override public Mono<StudentGradesEntity> findById(String id) {
        return template.select(StudentGradesEntity.class).matching(Query.query(Criteria.where("id").is(id))).one();
    }

    @Override public Flux<StudentGradesEntity> findAll() { 
        return template.select(StudentGradesEntity.class).all(); 
    }
    
    @Override public Mono<Void> deleteById(String id) {
        return template.delete(StudentGradesEntity.class).matching(Query.query(Criteria.where("id").is(id))).all().then();
    }
}