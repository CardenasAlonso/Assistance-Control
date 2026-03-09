package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageStudentScoreUseCase {
    Mono<ScoreResponseDto> registerScore(RegisterScoreCommand cmd);
    Mono<ScoreResponseDto> getScoreById(String id);
    Flux<ScoreResponseDto> getAllScores();
    Flux<ScoreResponseDto> getScoresByStudent(String studentId);
    Flux<ScoreResponseDto> getScoresByCourseAssignment(String courseAssignmentId);
    Flux<ScoreResponseDto> getScoresByDidacticUnit(String didacticUnitId);
    Mono<Double> calculateFinalAverage(String studentId, String courseAssignmentId);
    Mono<ScoreResponseDto> updateScore(String id, UpdateScoreCommand cmd);
    Mono<Void> deleteScore(String id);
}
