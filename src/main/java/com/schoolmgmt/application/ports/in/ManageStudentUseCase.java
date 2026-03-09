package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageStudentUseCase {
    Mono<StudentResponseDto> registerStudent(RegisterStudentCommand cmd);
    Mono<StudentResponseDto> getStudentById(String id);
    Mono<StudentResponseDto> getStudentByDocumentNumber(String documentNumber);
    Flux<StudentResponseDto> getAllStudents();
    Flux<StudentResponseDto> getAll();
    Mono<StudentResponseDto> getById(String id);
    Flux<StudentResponseDto> getStudentsBySection(String sectionId);
    Mono<StudentResponseDto> updateStudent(String id, UpdateStudentCommand cmd);
    Mono<Void> deleteStudent(String id);
}
