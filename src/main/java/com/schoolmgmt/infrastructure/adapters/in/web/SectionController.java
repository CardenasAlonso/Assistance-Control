package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.ports.out.SectionRepositoryPort;
import com.schoolmgmt.application.dto.CreateSectionCommand;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.SectionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionRepositoryPort sectionRepositoryPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SectionEntity> createSection(@RequestBody CreateSectionCommand command) {
        SectionEntity entity = SectionEntity.builder()
                .id(UUID.randomUUID().toString())
                .gradeId(command.gradeId())
                .name(command.name())
                .capacity(command.capacity())
                .turn(command.turn())
                .isActive(1) // Por defecto activo al crear
                .build();

        return sectionRepositoryPort.save(entity);
    }

    @GetMapping
    public Flux<SectionEntity> getAllSections() {
        return sectionRepositoryPort.findAll();
    }

    @GetMapping("/{id}")
    public Mono<SectionEntity> getSectionById(@PathVariable String id) {
        return sectionRepositoryPort.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<SectionEntity> updateSection(@PathVariable String id, @RequestBody CreateSectionCommand command) {
        return sectionRepositoryPort.findById(id)
                .flatMap(existingEntity -> {
                    // Actualizamos los campos
                    existingEntity.setGradeId(command.gradeId());
                    existingEntity.setName(command.name());
                    existingEntity.setCapacity(command.capacity());
                    existingEntity.setTurn(command.turn());
                    
                    return sectionRepositoryPort.update(existingEntity);
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteSection(@PathVariable String id) {
        // En tu Student veo que usaste deleteById que borra físicamente. 
        // Si quieres mantenerlo igual a Student, usamos el delete físico:
        return sectionRepositoryPort.deleteById(id);
        
        // (Si prefieres el borrado lógico como te mencioné antes, me avisas y lo cambiamos).
    }
}