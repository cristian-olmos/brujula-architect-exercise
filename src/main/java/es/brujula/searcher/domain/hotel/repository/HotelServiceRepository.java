package es.brujula.searcher.domain.hotel.repository;

public interface HotelServiceRepository {

    void add(String hotelId, String serviceId);

    void delete(String hotelId, String serviceId);
}
