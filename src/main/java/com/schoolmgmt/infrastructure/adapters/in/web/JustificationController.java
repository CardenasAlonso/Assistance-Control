package com.schoolmgmt.infrastructure.adapters.in.web;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.ManageJustificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/api/justifications") @RequiredArgsConstructor
public class JustificationController {
    private final ManageJustificationUseCase useCase;

    @PostMapping
    public Mono<ResponseEntity<JustificationResponseDto>> request(@RequestBody RequestJustificationCommand cmd) {
        return useCase.requestJustification(cmd).map(j -> ResponseEntity.status(HttpStatus.CREATED).body(j));
    }
    @PatchMapping("/{id}/approve")
    public Mono<ResponseEntity<JustificationResponseDto>> approve(
            @PathVariable String id, @RequestParam String reviewedBy) {
        return useCase.approveJustification(id, reviewedBy).map(ResponseEntity::ok);
    }
    @PatchMapping("/{id}/reject")
    public Mono<ResponseEntity<JustificationResponseDto>> reject(
            @PathVariable String id, @RequestParam String reviewedBy, @RequestParam String note) {
        return useCase.rejectJustification(id, reviewedBy, note).map(ResponseEntity::ok);
    }
    @GetMapping("/student/{studentId}")
    public Flux<JustificationResponseDto> getByStudent(@PathVariable String studentId) {
        return useCase.getByStudentId(studentId);
    }
    @GetMapping("/status/{status}")
    public Flux<JustificationResponseDto> getByStatus(@PathVariable String status) {
        return useCase.getByStatus(status);
    }
}
