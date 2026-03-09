package com.schoolmgmt.application.usecase;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.ManageDidacticUnitUseCase;
import com.schoolmgmt.application.ports.out.DidacticUnitRepositoryPort;
import com.schoolmgmt.domain.exception.ResourceNotFoundException;
import com.schoolmgmt.domain.model.DidacticUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ManageDidacticUnitUseCaseImpl implements ManageDidacticUnitUseCase {
    private final DidacticUnitRepositoryPort repository;

    @Override
    public Mono<DidacticUnitResponseDto> createUnit(CreateDidacticUnitCommand cmd) {
        DidacticUnit unit = DidacticUnit.builder().id(UUID.randomUUID().toString())
            .courseAssignmentId(cmd.courseAssignmentId()).unitNumber(cmd.unitNumber())
            .title(cmd.title()).description(cmd.description())
            .startDate(cmd.startDate()).endDate(cmd.endDate()).isActive(1).build();
        return repository.save(unit).map(this::toDto);
    }

    @Override
    public Mono<DidacticUnitResponseDto> getUnitById(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("DidacticUnit", id)))
            .map(this::toDto);
    }

    @Override
    public Flux<DidacticUnitResponseDto> getAllUnits() {
        return repository.findAll().map(this::toDto);
    }

    @Override
    public Flux<DidacticUnitResponseDto> getUnitsByAssignment(String courseAssignmentId) {
        return repository.findByCourseAssignmentId(courseAssignmentId).map(this::toDto);
    }

    @Override
    public Mono<DidacticUnitResponseDto> updateUnit(String id, CreateDidacticUnitCommand cmd) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("DidacticUnit", id)))
            .flatMap(u -> { u.setTitle(cmd.title()); u.setDescription(cmd.description());
                u.setStartDate(cmd.startDate()); u.setEndDate(cmd.endDate());
                return repository.update(u); })
            .map(this::toDto);
    }

    @Override
    public Mono<DidacticUnitResponseDto> closeUnit(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new ResourceNotFoundException("DidacticUnit", id)))
            .flatMap(u -> { u.setIsActive(0); return repository.update(u); })
            .map(this::toDto);
    }

    @Override
    public Mono<Void> deleteUnit(String id) { return repository.deleteById(id); }

    private DidacticUnitResponseDto toDto(DidacticUnit u) {
        return new DidacticUnitResponseDto(u.getId(), u.getCourseAssignmentId(), u.getUnitNumber(),
            u.getTitle(), u.getDescription(), u.getStartDate(), u.getEndDate(), u.getIsActive());
    }
}
