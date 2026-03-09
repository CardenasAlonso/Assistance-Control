package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageJustificationUseCase {
    Mono<JustificationResponseDto> requestJustification(RequestJustificationCommand cmd);
    Mono<JustificationResponseDto> approveJustification(String id, String reviewedBy);
    Mono<JustificationResponseDto> rejectJustification(String id, String reviewedBy, String note);
    Flux<JustificationResponseDto> getByStudentId(String studentId);
    Flux<JustificationResponseDto> getByStatus(String status);
}
