package com.schoolmgmt.infrastructure.adapters.out.database.mapper;
import com.schoolmgmt.domain.model.blockchain.BlockRecord;
import com.schoolmgmt.domain.enums.BlockEventType;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.BlockEntity;

public class BlockMapper {
    private BlockMapper() {}

    public static BlockRecord toDomain(BlockEntity e) {
        return BlockRecord.builder().id(e.getId()).blockIndex(e.getBlockIndex())
            .eventType(BlockEventType.valueOf(e.getEventType())).entityId(e.getEntityId())
            .entityType(e.getEntityType()).payload(e.getPayload())
            .previousHash(e.getPreviousHash()).hash(e.getHash())
            .createdBy(e.getCreatedBy()).timestamp(e.getCreatedAt()).build();
    }

    public static BlockEntity toEntity(BlockRecord b) {
        return BlockEntity.builder().id(b.getId()).blockIndex(b.getBlockIndex())
            .eventType(b.getEventType().name()).entityId(b.getEntityId())
            .entityType(b.getEntityType()).payload(b.getPayload())
            .previousHash(b.getPreviousHash()).hash(b.getHash())
            .createdBy(b.getCreatedBy()).createdAt(b.getTimestamp()).build();
    }
}
