package com.schoolmgmt.application.usecase;
import com.schoolmgmt.application.dto.GradeResponseDto;
import com.schoolmgmt.application.dto.RegisterGradeCommand;
import com.schoolmgmt.application.dto.UpdateGradeCommand;
import com.schoolmgmt.application.ports.out.StudentGradesRepositoryPort;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.StudentGradesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageStudentGradesUseCaseImpl {

    private final StudentGradesRepositoryPort gradesRepositoryPort;

    public Mono<GradeResponseDto> registerGrade(RegisterGradeCommand command) {
        if (command.score() < 0) {
            return Mono.error(new IllegalArgumentException("La nota no puede ser negativa"));
        }
        StudentGradesEntity grade = StudentGradesEntity.builder()
                .id(UUID.randomUUID().toString())
                .studentId(command.studentId())
                .courseAssignmentId(command.courseAssignmentId())
                .didacticUnitId(command.didacticUnitId())
                .score(command.score())
                .evaluationType(command.evaluationType())
                .build();
        return gradesRepositoryPort.save(grade).map(this::mapToDto);
    }

    public Mono<Void> updateGrade(String id, UpdateGradeCommand command) {
        return gradesRepositoryPort.findById(id)
                .flatMap(existing -> {
                    existing.setScore(command.score());
                    existing.setEvaluationType(command.evaluationType());
                    return gradesRepositoryPort.update(existing);
                }).then();
    }

    public Mono<GradeResponseDto> getById(String id) {
        return gradesRepositoryPort.findById(id).map(this::mapToDto);
    }

    public Flux<GradeResponseDto> getAll() {
        return gradesRepositoryPort.findAll().map(this::mapToDto);
    }

    public Mono<Void> deleteGrade(String id) {
        return gradesRepositoryPort.deleteById(id);
    }

    private GradeResponseDto mapToDto(StudentGradesEntity g) {
        return new GradeResponseDto(
            g.getId(), 
            g.getStudentId(), 
            g.getCourseAssignmentId(), 
            g.getDidacticUnitId(), 
            g.getScore(), 
            g.getEvaluationType()
        );
    }
}