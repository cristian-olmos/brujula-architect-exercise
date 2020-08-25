package es.brujula.searcher.application.query.hotel.viewservices;

public final class ViewHotelServicesQuery {

    private String hotelId;

    public ViewHotelServicesQuery(String hotelId) {

        this.hotelId = hotelId;
    }

    public String hotelId() {
        return hotelId;
    }
}
