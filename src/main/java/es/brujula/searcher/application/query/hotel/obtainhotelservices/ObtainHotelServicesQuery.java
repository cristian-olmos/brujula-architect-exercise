package es.brujula.searcher.application.query.hotel.obtainhotelservices;

public final class ObtainHotelServicesQuery {

    private final String hotelId;

    public ObtainHotelServicesQuery(String hotelId) {
        this.hotelId = hotelId;
    }

    public String hotelId() {
        return hotelId;
    }
}
