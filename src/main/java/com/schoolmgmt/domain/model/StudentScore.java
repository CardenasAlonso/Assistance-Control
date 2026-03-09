package com.schoolmgmt.domain.model;
import com.schoolmgmt.domain.enums.EvaluationType;
import com.schoolmgmt.domain.enums.PerformanceLevel;
import com.schoolmgmt.domain.exception.InvalidScoreException;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class StudentScore {
    private String id;
    private String studentId;
    private String courseAssignmentId;
    private String didacticUnitId;
    private Double score;
    private EvaluationType evaluationType;
    private String comments;
    private LocalDateTime registeredAt;

    public void validateScore() {
        if (score == null || score < 0 || score > 20)
            throw new InvalidScoreException(score == null ? -1 : score);
    }

    public PerformanceLevel getPerformanceLevel() {
        return score == null ? PerformanceLevel.EN_INICIO : PerformanceLevel.fromScore(score);
    }

    public boolean isApproved() { return score != null && score >= 11; }
}
