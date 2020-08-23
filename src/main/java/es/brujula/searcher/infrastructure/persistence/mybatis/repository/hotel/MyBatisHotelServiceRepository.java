package es.brujula.searcher.infrastructure.persistence.mybatis.repository.hotel;

import es.brujula.searcher.domain.hotel.repository.HotelServiceRepository;
import es.brujula.searcher.infrastructure.persistence.mybatis.mapper.hotel.HotelServiceMapper;
import org.springframework.stereotype.Component;

@Component
public class MyBatisHotelServiceRepository implements HotelServiceRepository {

    private HotelServiceMapper mapper;

    public MyBatisHotelServiceRepository(HotelServiceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void add(String hotelId, String serviceId) {
        this.mapper.add(hotelId, serviceId);
    }

    @Override
    public void delete(String hotelId, String serviceId) {
        this.mapper.delete(hotelId, serviceId);
    }
}
