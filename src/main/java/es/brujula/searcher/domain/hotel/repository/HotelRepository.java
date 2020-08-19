package es.brujula.searcher.domain.hotel.repository;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.valueobject.HotelId;

import java.util.Collection;
import java.util.Optional;

public interface HotelRepository {

    Collection<Hotel> search(PaginatedSearchParam searchParam);

    Integer count(PaginatedSearchParam searchParam);

    Optional<Hotel> byId(String id);

    Hotel byIdOrFail(String id);

    void add(Hotel hotel);

    void delete(String id);

    Collection<Hotel> all();

    void save(Hotel hotel);
}
