package com.schoolmgmt.application.ports.in;
import com.schoolmgmt.application.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlockchainServicePort {
    Mono<BlockResponseDto> sealRecord(SealRecordCommand cmd);
    Mono<Boolean> verifyChainIntegrity();
    Flux<BlockResponseDto> getChainByStudent(String studentId);
    Mono<BlockResponseDto> getLatestBlock();
}
