package com.schoolmgmt.infrastructure.adapters.out.database.mapper;
import com.schoolmgmt.domain.model.StudentScore;
import com.schoolmgmt.domain.enums.EvaluationType;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentGradesEntity;

public class StudentScoreMapper {
    private StudentScoreMapper() {}

    public static StudentScore toDomain(StudentGradesEntity e) {
        return StudentScore.builder()
            .id(e.getId()).studentId(e.getStudentId())
            .courseAssignmentId(e.getCourseAssignmentId())
            .didacticUnitId(e.getDidacticUnitId())
            .score(e.getScore())
            .evaluationType(e.getEvaluationType() != null ? EvaluationType.valueOf(e.getEvaluationType()) : null)
            .comments(e.getComments())
            .registeredAt(e.getRegisteredAt())
            .build();
    }

    public static StudentGradesEntity toEntity(StudentScore s) {
        return StudentGradesEntity.builder()
            .id(s.getId()).studentId(s.getStudentId())
            .courseAssignmentId(s.getCourseAssignmentId())
            .didacticUnitId(s.getDidacticUnitId())
            .score(s.getScore())
            .evaluationType(s.getEvaluationType() != null ? s.getEvaluationType().name() : null)
            .comments(s.getComments())
            .registeredAt(s.getRegisteredAt())
            .build();
    }
}
