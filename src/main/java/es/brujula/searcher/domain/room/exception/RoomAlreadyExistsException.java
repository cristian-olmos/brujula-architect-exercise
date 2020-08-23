package es.brujula.searcher.domain.room.exception;

import es.brujula.searcher.domain.DomainException;

@SuppressWarnings("serial")
public final class RoomAlreadyExistsException extends RuntimeException implements DomainException {

    private static final String MESSAGE = "ROOM_ALREADY_EXISTS";

    public RoomAlreadyExistsException(String id) {
        super(id);
    }

    public String getType() {
        return MESSAGE;
    }
}