package com.schoolmgmt.domain.exception;
public class BlockchainIntegrityException extends DomainException {
    public BlockchainIntegrityException(int blockIndex) {
        super("Integridad comprometida en el bloque #" + blockIndex + ". El hash no coincide con el registro.");
    }
}
