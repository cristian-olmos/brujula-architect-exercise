package es.brujula.searcher.infrastructure.persistence.mybatis.repository.hotel;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.application.query.hotel.viewall.SearchHotelParam;
import es.brujula.searcher.domain.hotel.exception.HotelNotFoundException;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.searcher.infrastructure.persistence.mybatis.mapper.hotel.HotelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class MyBatisHotelRepository implements HotelRepository {

    private HotelMapper mapper;

    public MyBatisHotelRepository(HotelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Integer count(PaginatedSearchParam searchParam) {
        return this.mapper.count(searchParam);
    }

    @Override
    public Hotel byIdOrFail(String hotelId) {

        return this.byId(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(hotelId));
    }


    @Override
    public Optional<Hotel> byId(String id) {
        return this.mapper.byId(id);
    }

    @Override
    public void add(Hotel hotel) {
        this.mapper.add(hotel);
    }

    @Override
    public void delete(String id) {
        this.mapper.delete(id);
    }

    @Override
    public Collection<Hotel> search(SearchHotelParam params) {
        return this.mapper.search(params);
    }

    @Override
    public void save(Hotel hotel) {
        this.mapper.update(hotel);
    }
}
