package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicLevelServicePort {
    Mono<AcademicLevelResponseDto> createLevel(CreateAcademicLevelCommand cmd);
    Mono<AcademicLevelResponseDto> getLevelById(String id);
    Flux<AcademicLevelResponseDto> getAllLevels();
    Mono<AcademicLevelResponseDto> updateLevel(String id, CreateAcademicLevelCommand cmd);
    Mono<Void> deleteLevel(String id);
}
