package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchoolYearServicePort {
    Mono<SchoolYearResponseDto> createYear(CreateSchoolYearCommand cmd);
    Mono<SchoolYearResponseDto> getYearById(String id);
    Flux<SchoolYearResponseDto> getAllYears();
    Mono<SchoolYearResponseDto> updateYear(String id, CreateSchoolYearCommand cmd);
    Mono<Void> deleteYear(String id);
}
