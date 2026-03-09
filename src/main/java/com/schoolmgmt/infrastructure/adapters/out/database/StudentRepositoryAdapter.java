package com.schoolmgmt.infrastructure.adapters.out.database;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.schoolmgmt.application.ports.out.StudentRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<StudentEntity> save(StudentEntity studentEntity) {
        return template.insert(studentEntity);
    }

    @Override
    public Mono<StudentEntity> update(StudentEntity studentEntity) {
        return template.update(studentEntity);
    }

    @Override
    public Mono<StudentEntity> findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.select(StudentEntity.class).matching(query).one();
    }

    @Override
    public Mono<StudentEntity> findByDocumentNumber(String documentNumber) {
        Query query = Query.query(Criteria.where("document_number").is(documentNumber));
        return template.select(StudentEntity.class).matching(query).one();
    }

    @Override
    public Flux<StudentEntity> findAll() {
        return template.select(StudentEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.delete(StudentEntity.class).matching(query).all().then();
    }
}