package com.schoolmgmt.domain.exception;
public class ResourceNotFoundException extends DomainException {
    public ResourceNotFoundException(String resource, String id) {
        super(resource + " no encontrado con ID: " + id);
    }
}
