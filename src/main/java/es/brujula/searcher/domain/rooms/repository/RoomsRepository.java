package es.brujula.searcher.domain.rooms.repository;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.hotel.model.Hotel;

import java.util.Collection;
import java.util.Optional;

public interface RoomsRepository {

    Collection<Hotel> search(PaginatedSearchParam searchParam);

    Integer count(PaginatedSearchParam searchParam);

    Optional<Hotel> byId(String id);

    Hotel byIdOrFail(String id);

    void add(Hotel hotel);

    void delete(String id);

    Collection<Hotel> all();

    void save(Hotel hotel);
}
