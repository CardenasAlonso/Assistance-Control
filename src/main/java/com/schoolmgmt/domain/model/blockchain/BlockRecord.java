package com.schoolmgmt.domain.model.blockchain;
import com.schoolmgmt.domain.enums.BlockEventType;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data @Builder
public class BlockRecord {
    private String id;
    private Integer blockIndex;
    private BlockEventType eventType;
    private String entityId;
    private String entityType;
    private String payload;
    private String previousHash;
    private String hash;
    private String createdBy;
    private Instant timestamp;
}
