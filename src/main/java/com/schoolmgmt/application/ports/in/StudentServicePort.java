package com.schoolmgmt.application.ports.in;

import com.schoolmgmt.application.dto.RegisterStudentCommand;
import com.schoolmgmt.application.dto.StudentResponseDto;
import com.schoolmgmt.application.dto.UpdateStudentCommand;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentServicePort {

    Mono<StudentResponseDto> createStudent(RegisterStudentCommand command);
    Mono<StudentResponseDto> getStudentById(String id);
    Flux<StudentResponseDto> getAllStudents();
    Mono<StudentResponseDto> updateStudent(String id, UpdateStudentCommand command);
    Mono<Void> deleteStudent(String id);

}
