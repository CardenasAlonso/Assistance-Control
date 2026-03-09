package com.schoolmgmt.infrastructure.adapters.out.database;

import com.schoolmgmt.application.dto.AttendanceResponseDto;
import com.schoolmgmt.application.ports.out.AttendanceRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.AttendancesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria; // ¡Importante!
import org.springframework.data.relational.core.query.Query;    // ¡Importante!
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AttendanceRepositoryAdapter implements AttendanceRepositoryPort {

    private final R2dbcEntityTemplate template;
    // Ya no inyectamos el repository aquí

    @Override
    public Mono<AttendancesEntity> save(AttendancesEntity attendance) {
        return template.insert(attendance);
    }

    @Override
    public Flux<AttendancesEntity> saveAll(Iterable<AttendancesEntity> attendances) {
        return Flux.fromIterable(attendances)
                .flatMap(template::insert);
    }

    @Override
    public Flux<AttendanceResponseDto> findByStudentIdAndAttendanceDate(String studentId, LocalDate date) {
        // Sintaxis correcta y segura de Spring Data R2DBC para consultas
        Query query = Query.query(Criteria.where("student_id").is(studentId)
                .and("attendance_date").is(date));
    }

    @Override
    public Mono<AttendanceResponseDto> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Flux<AttendanceResponseDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}