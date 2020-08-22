package es.brujula.searcher.domain.rooms.repository;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.rooms.model.Room;

import java.util.Collection;
import java.util.Optional;

public interface HotelRepository {

    Collection<Room> search(PaginatedSearchParam searchParam);

    Integer count(PaginatedSearchParam searchParam);

    Optional<Room> byId(String id);

    Room byIdOrFail(String id);

    void add(Room hotel);

    void delete(String id);

    Collection<Room> all();

    void save(Room hotel);
}
