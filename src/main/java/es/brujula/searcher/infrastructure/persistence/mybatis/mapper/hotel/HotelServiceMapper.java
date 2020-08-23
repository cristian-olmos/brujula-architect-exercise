package es.brujula.searcher.infrastructure.persistence.mybatis.mapper.hotel;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotelServiceMapper {

    void add(String hotelId, String serviceId);

    void delete(String hotelId, String serviceId);
}
