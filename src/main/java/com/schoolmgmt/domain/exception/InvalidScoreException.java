package com.schoolmgmt.domain.exception;
public class InvalidScoreException extends DomainException {
    public InvalidScoreException(double score) {
        super("Nota inválida: " + score + ". El rango permitido es 0.00 – 20.00");
    }
}
