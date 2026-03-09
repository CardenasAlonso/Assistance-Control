package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageCourseAssignmentUseCase {
    Mono<CourseAssignmentResponseDto> register(RegisterCourseAssignmentCommand cmd);
    Mono<CourseAssignmentResponseDto> getById(String id);
    Flux<CourseAssignmentResponseDto> getAll();
    Flux<CourseAssignmentResponseDto> getByTeacherId(String teacherId);
    Flux<CourseAssignmentResponseDto> getBySectionId(String sectionId);
    Flux<CourseAssignmentResponseDto> getByPeriodId(String periodId);
    Mono<CourseAssignmentResponseDto> update(String id, RegisterCourseAssignmentCommand cmd);
    Mono<Void> delete(String id);
}
