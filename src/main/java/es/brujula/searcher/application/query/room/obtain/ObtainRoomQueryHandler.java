package es.brujula.searcher.application.query.room.obtain;

import es.brujula.searcher.domain.room.model.Room;
import es.brujula.searcher.domain.room.repository.RoomRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public final class ObtainRoomQueryHandler implements QueryHandler<Room, ObtainRoomQuery> {

    private RoomRepository rooms;

    public ObtainRoomQueryHandler(RoomRepository rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room handle(ObtainRoomQuery query) {
        String id = query.roomId();

        return rooms.byIdOrFail(id);
    }
}
