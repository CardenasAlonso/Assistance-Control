package com.schoolmgmt.infrastructure.adapters.in.web;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.ManageDidacticUnitUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/api/didactic-units") @RequiredArgsConstructor
public class DidacticUnitController {
    private final ManageDidacticUnitUseCase useCase;

    @PostMapping
    public Mono<ResponseEntity<DidacticUnitResponseDto>> create(@RequestBody CreateDidacticUnitCommand cmd) {
        return useCase.createUnit(cmd).map(d -> ResponseEntity.status(HttpStatus.CREATED).body(d));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<DidacticUnitResponseDto>> getById(@PathVariable String id) {
        return useCase.getUnitById(id).map(ResponseEntity::ok);
    }
    @GetMapping
    public Flux<DidacticUnitResponseDto> getAll() { return useCase.getAllUnits(); }
    @GetMapping("/by-assignment/{courseAssignmentId}")
    public Flux<DidacticUnitResponseDto> getByAssignment(@PathVariable String courseAssignmentId) {
        return useCase.getUnitsByAssignment(courseAssignmentId);
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<DidacticUnitResponseDto>> update(@PathVariable String id, @RequestBody CreateDidacticUnitCommand cmd) {
        return useCase.updateUnit(id, cmd).map(ResponseEntity::ok);
    }
    @PatchMapping("/{id}/close")
    public Mono<ResponseEntity<DidacticUnitResponseDto>> close(@PathVariable String id) {
        return useCase.closeUnit(id).map(ResponseEntity::ok);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return useCase.deleteUnit(id).then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
