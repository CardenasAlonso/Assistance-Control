package com.schoolmgmt.application.usecase;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.ManageJustificationUseCase;
import com.schoolmgmt.application.ports.out.JustificationRepositoryPort;
import com.schoolmgmt.domain.exception.ResourceNotFoundException;
import com.schoolmgmt.domain.model.Justification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ManageJustificationUseCaseImpl implements ManageJustificationUseCase {
    private final JustificationRepositoryPort repository;

    @Override
    public Mono<JustificationResponseDto> requestJustification(RequestJustificationCommand cmd) {
        Justification j = Justification.builder().id(UUID.randomUUID().toString())
            .attendanceId(cmd.attendanceId()).studentId(cmd.studentId())
            .requestedBy(cmd.requestedBy()).reason(cmd.reason())
            .documentUrl(cmd.documentUrl()).status("PENDING")
            .createdAt(LocalDateTime.now()).build();
        return repository.save(j).map(this::toDto);
    }

    @Override
    public Mono<JustificationResponseDto> approveJustification(String id, String reviewedBy) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("Justification", id)))
            .flatMap(j -> { j.setStatus("APPROVED"); j.setReviewedBy(reviewedBy);
                j.setReviewedAt(LocalDateTime.now()); return repository.update(j); })
            .map(this::toDto);
    }

    @Override
    public Mono<JustificationResponseDto> rejectJustification(String id, String reviewedBy, String note) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("Justification", id)))
            .flatMap(j -> { j.setStatus("REJECTED"); j.setReviewedBy(reviewedBy);
                j.setRejectionNote(note); j.setReviewedAt(LocalDateTime.now()); return repository.update(j); })
            .map(this::toDto);
    }

    @Override
    public Flux<JustificationResponseDto> getByStudentId(String studentId) {
        return repository.findByStudentId(studentId).map(this::toDto);
    }

    @Override
    public Flux<JustificationResponseDto> getByStatus(String status) {
        return repository.findByStatus(status).map(this::toDto);
    }

    private JustificationResponseDto toDto(Justification j) {
        return new JustificationResponseDto(j.getId(), j.getAttendanceId(), j.getStudentId(),
            j.getRequestedBy(), j.getReviewedBy(), j.getReason(), j.getStatus(),
            j.getRejectionNote(), j.getCreatedAt(), j.getReviewedAt());
    }
}
