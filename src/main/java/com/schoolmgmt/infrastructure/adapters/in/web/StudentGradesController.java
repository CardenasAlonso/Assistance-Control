package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.GradeResponseDto;
import com.schoolmgmt.application.dto.RegisterGradeCommand;
import com.schoolmgmt.application.dto.UpdateGradeCommand;
import com.schoolmgmt.application.usecase.ManageStudentGradesUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/grades-students")
@RequiredArgsConstructor
public class StudentGradesController {

    private final ManageStudentGradesUseCaseImpl useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GradeResponseDto> registerGrade(@RequestBody RegisterGradeCommand command) { 
        return useCase.registerGrade(command); 
    }

    @GetMapping
    public Flux<GradeResponseDto> getAllGrades() { 
        return useCase.getAll(); 
    }

    @GetMapping("/{id}")
    public Mono<GradeResponseDto> getById(@PathVariable String id) { 
        return useCase.getById(id); 
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateGrade(@PathVariable String id, @RequestBody UpdateGradeCommand command) { 
        return useCase.updateGrade(id, command); 
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteGrade(@PathVariable String id) { 
        return useCase.deleteGrade(id); 
    }
}