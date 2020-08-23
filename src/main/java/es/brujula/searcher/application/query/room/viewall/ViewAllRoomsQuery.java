package es.brujula.searcher.application.query.room.viewall;

public final class ViewAllRoomsQuery {
    private String hotelId;

    public ViewAllRoomsQuery(String hotelId) {
        this.hotelId = hotelId;
    }

    public String hotelId() {
        return hotelId;
    }
}
