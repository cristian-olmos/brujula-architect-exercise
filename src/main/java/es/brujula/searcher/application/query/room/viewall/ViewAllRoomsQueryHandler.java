package es.brujula.searcher.application.query.room.viewall;

import es.brujula.searcher.domain.room.model.Room;
import es.brujula.searcher.domain.room.repository.RoomRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class ViewAllRoomsQueryHandler implements QueryHandler<Collection<Room>, ViewAllRoomsQuery> {

    private final RoomRepository rooms;

    public ViewAllRoomsQueryHandler(RoomRepository rooms) {
        this.rooms = rooms;
    }


    @Override
    public Collection<Room> handle(ViewAllRoomsQuery query) {
        return rooms.all();
    }
}
