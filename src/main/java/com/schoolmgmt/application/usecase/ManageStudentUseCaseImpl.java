package com.schoolmgmt.application.usecase;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmgmt.application.dto.RegisterStudentCommand;
import com.schoolmgmt.application.dto.StudentResponseDto;
import com.schoolmgmt.application.dto.UpdateStudentCommand;
import com.schoolmgmt.application.ports.out.StudentRepositoryPort;
import com.schoolmgmt.application.ports.out.UserRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentEntity;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ManageStudentUseCaseImpl {

    private final UserRepositoryPort userRepositoryPort;
    private final StudentRepositoryPort studentRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Transactional 
    public Mono<StudentResponseDto> registerStudent(RegisterStudentCommand command) { // <-- Cambiado el nombre y el retorno a DTO

        String newUserId = UUID.randomUUID().toString();
        String newStudentId = UUID.randomUUID().toString();

        UserEntity newUser = UserEntity.builder()
                .id(newUserId)
                .email(command.email())
                .passwordHash(passwordEncoder.encode(command.password()))
                .firstName(command.firstName())
                .lastName(command.lastName())
                .role("STUDENT")
                .isActive(1)
                .createdAt(LocalDateTime.now())
                .build();

        StudentEntity newStudent = StudentEntity.builder()
                .id(newStudentId) 
                .userId(newUser.getId())
                .sectionId(command.sectionId())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .documentNumber(command.documentNumber())
                .birthDate(command.birthDate())
                .phone(command.phone())
                .address(command.address())
                .bloodType(command.bloodType())
                .status("ACTIVO")
                .build();

        return userRepositoryPort.save(newUser)
                .then(studentRepositoryPort.save(newStudent))
                .map(this::mapToDto); // <-- Convertimos la entidad a DTO antes de enviarla al Controller
    }

    public Mono<Void> updateStudent(String id, UpdateStudentCommand command) {
        return studentRepositoryPort.findById(id)
                .flatMap(existingStudent -> {
                    existingStudent.setFirstName(command.firstName());
                    existingStudent.setLastName(command.lastName());
                    existingStudent.setDocumentNumber(command.documentNumber());
                    existingStudent.setSectionId(command.sectionId());
                    existingStudent.setBirthDate(command.birthDate());
                    existingStudent.setPhone(command.phone());
                    existingStudent.setAddress(command.address());
                    existingStudent.setBloodType(command.bloodType());
                    return studentRepositoryPort.save(existingStudent);
                })
                .then();
    }

    public Mono<StudentResponseDto> getById(String id) {
        return studentRepositoryPort.findById(id).map(this::mapToDto);
    }

    public Flux<StudentResponseDto> getAll() {
        return studentRepositoryPort.findAll().map(this::mapToDto);
    }

    @Transactional 
    public Mono<Void> deleteStudent(String studentId) { // <-- Cambiado el nombre de 'execute' a 'deleteStudent'
        return studentRepositoryPort.findById(studentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Estudiante no encontrado")))
                .flatMap(student -> 
                    studentRepositoryPort.deleteById(student.getId())
                        .then(userRepositoryPort.deleteById(student.getUserId()))
                );
    }

    // --- Método auxiliar para no repetir código ---
    private StudentResponseDto mapToDto(StudentEntity student) {
        return new StudentResponseDto(
                student.getId(),
                student.getUserId(), // <-- Asegúrate de que tu StudentResponseDto tenga este campo
                student.getSectionId(), 
                student.getFirstName(),
                student.getLastName(),
                student.getBirthDate(),
                student.getDocumentNumber(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getBloodType(),
                student.getStatus()
        );
    }
}