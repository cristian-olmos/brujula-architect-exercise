package es.brujula.searcher.infrastructure.persistence.mybatis.mapper.room;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.room.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface RoomMapper {

    // TODO @SelectProvider
    Collection<Room> search(PaginatedSearchParam searchParam);

    Integer count(PaginatedSearchParam searchParam);

    Optional<Room> byId(@Param("id") String id);

    void add(Room room);

    void delete(String id);

    Collection<Room> byHotelId(String hotelId);

    void update(Room room);
}
