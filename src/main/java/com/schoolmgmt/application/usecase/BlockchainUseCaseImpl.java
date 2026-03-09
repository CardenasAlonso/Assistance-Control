package com.schoolmgmt.application.usecase;
import com.schoolmgmt.application.dto.*;
import com.schoolmgmt.application.ports.in.BlockchainServicePort;
import com.schoolmgmt.application.ports.out.BlockchainRepositoryPort;
import com.schoolmgmt.domain.enums.BlockEventType;
import com.schoolmgmt.domain.exception.BlockchainIntegrityException;
import com.schoolmgmt.domain.model.blockchain.BlockRecord;
import com.schoolmgmt.infrastructure.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Instant;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class BlockchainUseCaseImpl implements BlockchainServicePort {
    private final BlockchainRepositoryPort blockchainRepository;

    @Override
    public Mono<BlockResponseDto> sealRecord(SealRecordCommand cmd) {
        return blockchainRepository.findLatestBlock()
            .switchIfEmpty(Mono.just(BlockRecord.builder().blockIndex(-1).hash("0".repeat(64)).build()))
            .flatMap(latest -> {
                int newIndex = latest.getBlockIndex() + 1;
                Instant now = Instant.now();
                String hash = HashUtil.computeBlockHash(newIndex, cmd.eventType(),
                    cmd.payload(), latest.getHash(), now.toString());
                BlockRecord block = BlockRecord.builder()
                    .id(UUID.randomUUID().toString()).blockIndex(newIndex)
                    .eventType(BlockEventType.valueOf(cmd.eventType()))
                    .entityId(cmd.entityId()).entityType(cmd.entityType())
                    .payload(cmd.payload()).previousHash(latest.getHash())
                    .hash(hash).createdBy(cmd.createdBy()).timestamp(now).build();
                return blockchainRepository.saveBlock(block);
            })
            .map(b -> new BlockResponseDto(b.getId(), b.getBlockIndex(), b.getEventType().name(),
                b.getEntityId(), b.getEntityType(), b.getPayload(), b.getPreviousHash(),
                b.getHash(), b.getCreatedBy(), b.getTimestamp(), true));
    }

    @Override
    public Mono<Boolean> verifyChainIntegrity() {
        return blockchainRepository.findAll()
            .collectList()
            .map(blocks -> {
                for (int i = 1; i < blocks.size(); i++) {
                    BlockRecord b = blocks.get(i);
                    String expected = HashUtil.computeBlockHash(b.getBlockIndex(),
                        b.getEventType().name(), b.getPayload(),
                        b.getPreviousHash(), b.getTimestamp().toString());
                    if (!expected.equals(b.getHash())) throw new BlockchainIntegrityException(b.getBlockIndex());
                }
                return true;
            });
    }

    @Override
    public Flux<BlockResponseDto> getChainByStudent(String studentId) {
        return blockchainRepository.findByEntityId(studentId)
            .map(b -> new BlockResponseDto(b.getId(), b.getBlockIndex(), b.getEventType().name(),
                b.getEntityId(), b.getEntityType(), b.getPayload(), b.getPreviousHash(),
                b.getHash(), b.getCreatedBy(), b.getTimestamp(), true));
    }

    @Override
    public Mono<BlockResponseDto> getLatestBlock() {
        return blockchainRepository.findLatestBlock()
            .map(b -> new BlockResponseDto(b.getId(), b.getBlockIndex(), b.getEventType().name(),
                b.getEntityId(), b.getEntityType(), b.getPayload(), b.getPreviousHash(),
                b.getHash(), b.getCreatedBy(), b.getTimestamp(), true));
    }
}
