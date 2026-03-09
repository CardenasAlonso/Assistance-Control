package com.schoolmgmt.application.ports.out;
import com.schoolmgmt.domain.model.blockchain.BlockRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlockchainRepositoryPort {
    Mono<BlockRecord> saveBlock(BlockRecord block);
    Mono<BlockRecord> findLatestBlock();
    Flux<BlockRecord> findAll();
    Flux<BlockRecord> findByEntityId(String entityId);
    Mono<Integer> countBlocks();
}
