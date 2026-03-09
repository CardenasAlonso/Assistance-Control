package com.schoolmgmt.infrastructure.adapters.in.web;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.BlockchainServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/api/blockchain") @RequiredArgsConstructor
public class BlockchainController {
    private final BlockchainServicePort blockchainService;

    @PostMapping("/seal")
    public Mono<ResponseEntity<BlockResponseDto>> seal(@RequestBody SealRecordCommand cmd) {
        return blockchainService.sealRecord(cmd).map(ResponseEntity::ok);
    }
    @GetMapping("/verify")
    public Mono<ResponseEntity<Boolean>> verify() {
        return blockchainService.verifyChainIntegrity().map(ResponseEntity::ok);
    }
    @GetMapping("/student/{studentId}")
    public Flux<BlockResponseDto> getByStudent(@PathVariable String studentId) {
        return blockchainService.getChainByStudent(studentId);
    }
    @GetMapping("/latest")
    public Mono<ResponseEntity<BlockResponseDto>> getLatest() {
        return blockchainService.getLatestBlock().map(ResponseEntity::ok);
    }
}
