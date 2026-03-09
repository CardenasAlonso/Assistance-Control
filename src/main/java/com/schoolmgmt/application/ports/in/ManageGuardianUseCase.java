package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageGuardianUseCase {
    Mono<GuardianResponseDto> registerGuardian(RegisterGuardianCommand cmd);
    Mono<GuardianResponseDto> getGuardianById(String id);
    Flux<GuardianResponseDto> getAllGuardians();
    Flux<GuardianResponseDto> getGuardiansByStudentId(String studentId);
    Mono<GuardianResponseDto> updateGuardian(String id, RegisterGuardianCommand cmd);
    Mono<Void> deleteGuardian(String id);
}
