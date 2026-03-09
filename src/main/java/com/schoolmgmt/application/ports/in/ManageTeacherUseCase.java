package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageTeacherUseCase {
    Mono<TeacherResponseDto> createTeacher(CreateTeacherCommand cmd);
    Mono<TeacherResponseDto> getTeacherById(String id);
    Flux<TeacherResponseDto> getAllTeachers();
    Mono<TeacherResponseDto> updateTeacher(String id, UpdateTeacherCommand cmd);
    Mono<TeacherResponseDto> changeStatus(String id, String status);
    Mono<Void> deleteTeacher(String id);
}
