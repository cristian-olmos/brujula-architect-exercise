package es.brujula.searcher.domain.room.exception;

import es.brujula.searcher.domain.DomainException;

public final class RoomNotFoundException extends RuntimeException implements DomainException {

    private static final String MESSAGE = "HOTEL_NOT_FOUND";

    public RoomNotFoundException(String id) {
        super(id);
    }

    public String getType() {
        return MESSAGE;
    }
}