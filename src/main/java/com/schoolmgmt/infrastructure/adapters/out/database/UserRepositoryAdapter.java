package com.schoolmgmt.infrastructure.adapters.out.database;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.schoolmgmt.application.ports.out.UserRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<UserEntity> save(UserEntity userEntity) {
        return template.insert(userEntity);
    }

    @Override
    public Mono<UserEntity> update(UserEntity userEntity) {
        return template.update(userEntity);
    }

    @Override
    public Mono<UserEntity> findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.select(UserEntity.class).matching(query).one();
    }

    @Override
    public Mono<UserEntity> findByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return template.select(UserEntity.class).matching(query).one();
    }

    @Override
    public Flux<UserEntity> findAll() {
        return template.select(UserEntity.class).all();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return template.delete(UserEntity.class).matching(query).all().then(); 
        // .then() convierte el resultado (número de filas borradas) en un Mono<Void>
    }
}