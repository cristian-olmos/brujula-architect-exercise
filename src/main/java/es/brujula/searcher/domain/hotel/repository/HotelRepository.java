package es.brujula.searcher.domain.hotel.repository;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.application.query.hotel.viewall.SearchHotelParam;
import es.brujula.searcher.domain.hotel.model.Hotel;

import java.util.Collection;
import java.util.Optional;

public interface HotelRepository {

    Integer count(PaginatedSearchParam searchParam);

    Optional<Hotel> byId(String id);

    Hotel byIdOrFail(String id);

    void add(Hotel hotel);

    void delete(String id);

    Collection<Hotel> search(SearchHotelParam params);

    void save(Hotel hotel);
}
