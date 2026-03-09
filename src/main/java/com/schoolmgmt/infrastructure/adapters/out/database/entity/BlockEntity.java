package com.schoolmgmt.infrastructure.adapters.out.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("BLOCKCHAIN_BLOCKS")
public class BlockEntity {
    
    @Id
    @Column("id")
    private String id;
    
    @Column("block_index")
    private Integer blockIndex;
    
    @Column("event_type")
    private String eventType;
    
    @Column("entity_id")
    private String entityId;
    
    @Column("entity_type")
    private String entityType;
    
    @Column("payload")
    private String payload;
    
    @Column("previous_hash")
    private String previousHash;
    
    @Column("hash")
    private String hash;
    
    @Column("created_by")
    private String createdBy;
    
    @Column("created_at")
    private Instant createdAt;
}
