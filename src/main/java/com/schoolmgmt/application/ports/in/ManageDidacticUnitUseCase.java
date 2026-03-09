package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageDidacticUnitUseCase {
    Mono<DidacticUnitResponseDto> createUnit(CreateDidacticUnitCommand cmd);
    Mono<DidacticUnitResponseDto> getUnitById(String id);
    Flux<DidacticUnitResponseDto> getAllUnits();
    Flux<DidacticUnitResponseDto> getUnitsByAssignment(String courseAssignmentId);
    Mono<DidacticUnitResponseDto> updateUnit(String id, CreateDidacticUnitCommand cmd);
    Mono<DidacticUnitResponseDto> closeUnit(String id);
    Mono<Void> deleteUnit(String id);
}
