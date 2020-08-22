package es.brujula.searcher.domain.rooms.exception;

import es.brujula.searcher.domain.DomainException;

@SuppressWarnings("serial")
public final class HotelAlreadyExistsException extends RuntimeException implements DomainException {

    private static final String MESSAGE = "EXAMPLE_ALREADY_EXISTS";

    public HotelAlreadyExistsException(String id) {
        super(id);
    }

    public String getType() {
        return MESSAGE;
    }
}