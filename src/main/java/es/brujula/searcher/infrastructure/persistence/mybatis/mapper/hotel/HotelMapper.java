package es.brujula.searcher.infrastructure.persistence.mybatis.mapper.hotel;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.application.query.hotel.search.SearchHotelParam;
import es.brujula.searcher.domain.hotel.model.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface HotelMapper {

    Integer count(PaginatedSearchParam searchParam);

    Optional<Hotel> byId(@Param("id") String id);

    void add(Hotel hotel);

    void delete(String id);

    Collection<Hotel> search(SearchHotelParam params);

    Collection<Hotel> all();

    void update(Hotel hotel);
}
