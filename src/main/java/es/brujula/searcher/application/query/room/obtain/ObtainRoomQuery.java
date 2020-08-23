package es.brujula.searcher.application.query.room.obtain;

public final class ObtainRoomQuery {

    private final String roomId;

    public ObtainRoomQuery(String roomId) {
        this.roomId = roomId;
    }

    public String roomId() {
        return roomId;
    }
}
