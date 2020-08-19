package es.brujula.searcher.application.query.hotel.obtain;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.searcher.domain.hotel.valueobject.HotelId;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public final class ObtainHotelQueryHandler implements QueryHandler<Hotel, ObtainHotelQuery> {

    private HotelRepository hotels;

    public ObtainHotelQueryHandler(HotelRepository hotels) {
        this.hotels = hotels;
    }

    @Override
    public Hotel handle(ObtainHotelQuery query) {
        String id = query.hotelId();

        return hotels.byIdOrFail(id);
    }
}
