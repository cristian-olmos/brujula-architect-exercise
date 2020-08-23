package es.brujula.searcher.infrastructure.persistence.mybatis.repository.room;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.room.exception.RoomNotFoundException;
import es.brujula.searcher.domain.room.model.Room;
import es.brujula.searcher.domain.room.repository.RoomRepository;
import es.brujula.searcher.infrastructure.persistence.mybatis.mapper.room.RoomMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class MyBatisRoomRepository implements RoomRepository {

    private RoomMapper mapper;

    public MyBatisRoomRepository(RoomMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Collection<Room> search(PaginatedSearchParam searchParam) {
        return this.mapper.search(searchParam);
    }

    @Override
    public Integer count(PaginatedSearchParam searchParam) {
        return this.mapper.count(searchParam);
    }

    @Override
    public Room byIdOrFail(String roomId) {

        return this.byId(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));
    }


    @Override
    public Optional<Room> byId(String id) {
        return this.mapper.byId(id);
    }

    @Override
    public void add(Room room) {
        this.mapper.add(room);
    }

    @Override
    public void delete(String id) {
        this.mapper.delete(id);
    }

    @Override
    public Collection<Room> all() {
        return this.mapper.all();
    }

    @Override
    public void save(Room room) {
        this.mapper.update(room);
    }
}
