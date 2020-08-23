package es.brujula.searcher.domain.service.exception;

import es.brujula.searcher.domain.DomainException;

public final class ServiceNotFoundException extends RuntimeException implements DomainException {

    private static final String MESSAGE = "HOTEL_NOT_FOUND";

    public ServiceNotFoundException(String id) {
        super(id);
    }

    public String getType() {
        return MESSAGE;
    }
}