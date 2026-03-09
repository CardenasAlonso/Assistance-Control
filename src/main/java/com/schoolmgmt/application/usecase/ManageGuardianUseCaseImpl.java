package com.schoolmgmt.application.usecase;

import com.schoolmgmt.application.dto.GuardianResponseDto;
import com.schoolmgmt.application.dto.RegisterGuardianCommand;
import com.schoolmgmt.application.dto.UpdateGuardianCommand;
import com.schoolmgmt.application.ports.out.GuardianRepositoryPort;
import com.schoolmgmt.application.ports.out.UserRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.GuardianEntity;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageGuardianUseCaseImpl {

    private final UserRepositoryPort userRepositoryPort;
    private final GuardianRepositoryPort guardianRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Mono<GuardianResponseDto> registerGuardian(RegisterGuardianCommand command) {
        String newUserId = UUID.randomUUID().toString();
        String newGuardianId = UUID.randomUUID().toString();

        UserEntity newUser = UserEntity.builder()
                .id(newUserId)
                .email(command.email())
                .passwordHash(passwordEncoder.encode(command.password()))
                .firstName(command.firstName())
                .lastName(command.lastName())
                .role("GUARDIAN")
                .isActive(1)
                .createdAt(LocalDateTime.now())
                .build();

        GuardianEntity newGuardian = GuardianEntity.builder()
                .id(newGuardianId)
                .studentId(command.studentId()) // Nuevo
                .userId(newUser.getId())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .relationship(command.relationship()) // Nuevo
                .documentNumber(command.documentNumber())
                .phone(command.phone())
                .email(command.email()) // Nuevo (se guarda también en esta tabla)
                .address(command.address())
                .isPrimaryContact(command.isPrimaryContact()) // Nuevo
                .build();

        return userRepositoryPort.save(newUser)
                .then(guardianRepositoryPort.save(newGuardian))
                .map(this::mapToDto); 
    }

    public Mono<Void> updateGuardian(String id, UpdateGuardianCommand command) {
        return guardianRepositoryPort.findById(id)
                .flatMap(existing -> {
                    existing.setFirstName(command.firstName());
                    existing.setLastName(command.lastName());
                    existing.setRelationship(command.relationship());
                    existing.setDocumentNumber(command.documentNumber());
                    existing.setPhone(command.phone());
                    existing.setAddress(command.address());
                    existing.setIsPrimaryContact(command.isPrimaryContact());
                    return guardianRepositoryPort.update(existing);
                }).then();
    }

    public Mono<GuardianResponseDto> getById(String id) {
        return guardianRepositoryPort.findById(id).map(this::mapToDto);
    }

    public Flux<GuardianResponseDto> getAll() {
        return guardianRepositoryPort.findAll().map(this::mapToDto);
    }

    @Transactional
    public Mono<Void> deleteGuardian(String guardianId) {
        return guardianRepositoryPort.findById(guardianId)
                .switchIfEmpty(Mono.error(new RuntimeException("Apoderado no encontrado")))
                .flatMap(guardian -> 
                    guardianRepositoryPort.deleteById(guardian.getId())
                        .then(userRepositoryPort.deleteById(guardian.getUserId()))
                );
    }

    private GuardianResponseDto mapToDto(GuardianEntity g) {
        return new GuardianResponseDto(
                g.getId(), g.getStudentId(), g.getUserId(), g.getFirstName(), 
                g.getLastName(), g.getRelationship(), g.getDocumentNumber(), 
                g.getEmail(), g.getPhone(), g.getAddress(), g.getIsPrimaryContact()
        );
    }
}