package es.brujula.searcher.domain.hotel.exception;

import es.brujula.searcher.domain.DomainException;

public final class HotelNotFoundException extends RuntimeException implements DomainException {

    private static final String MESSAGE = "HOTEL_NOT_FOUND";

    public HotelNotFoundException(String id) {
        super(id);
    }

    public String getType() {
        return MESSAGE;
    }
}