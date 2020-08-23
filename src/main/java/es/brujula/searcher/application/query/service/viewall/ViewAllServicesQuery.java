package es.brujula.searcher.application.query.service.viewall;

public final class ViewAllServicesQuery {

    private String hotelId;

    public ViewAllServicesQuery(String hotelId) {

        this.hotelId = hotelId;
    }

    public String hotelId() {
        return hotelId;
    }
}
