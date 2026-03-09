package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

public interface ManageCourseUseCase {
    Mono<CourseResponseDto> createCourse(CreateCourseCommand cmd);
    Mono<CourseResponseDto> getCourseById(String id);
    Mono<Map<String, Object>> getAllCourses();
    Flux<CourseResponseDto> getCoursesByAcademicLevel(String levelId);
    Mono<CourseAssignmentResponseDto> assignCourse(RegisterCourseAssignmentCommand cmd);
    Mono<Void> deleteCourse(String id);
}
