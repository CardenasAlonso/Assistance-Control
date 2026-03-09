package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageAcademicPeriodUseCase {
    Mono<AcademicPeriodResponseDto> createPeriod(CreateAcademicPeriodCommand cmd);
    Mono<AcademicPeriodResponseDto> getPeriodById(String id);
    Flux<AcademicPeriodResponseDto> getAllPeriods();
    Mono<AcademicPeriodResponseDto> getActivePeriod();
    Mono<AcademicPeriodResponseDto> activatePeriod(String id);
    Mono<AcademicPeriodResponseDto> deactivatePeriod(String id);
    Mono<Void> deletePeriod(String id);
}
