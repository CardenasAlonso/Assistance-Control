package com.schoolmgmt.infrastructure.adapters.in.web;

import com.schoolmgmt.application.dto.AuditLogResponseDto;
import com.schoolmgmt.application.dto.CreateAuditLogCommand;
import com.schoolmgmt.application.usecase.ManageAuditLogUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {
    
    private final ManageAuditLogUseCaseImpl useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AuditLogResponseDto> createLog(@RequestBody CreateAuditLogCommand command) { 
        return useCase.logAction(command); 
    }

    @GetMapping
    public Flux<AuditLogResponseDto> getAll() { 
        return useCase.getAllLogs(); 
    }

    @GetMapping("/{id}")
    public Mono<AuditLogResponseDto> getById(@PathVariable String id) { 
        return useCase.getLogById(id); 
    }
}