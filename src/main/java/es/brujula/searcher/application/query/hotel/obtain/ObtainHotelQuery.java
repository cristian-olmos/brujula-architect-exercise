package es.brujula.searcher.application.query.hotel.obtain;

public final class ObtainHotelQuery {

    private final String hotelId;

    public ObtainHotelQuery(String hotelId) {
        this.hotelId = hotelId;
    }

    public String hotelId() {
        return hotelId;
    }
}
