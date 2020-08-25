package es.brujula.searcher.application.query.hotel.viewall;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class ViewAllHotelsQueryHandler implements QueryHandler<Collection<Hotel>, ViewAllHotelsQuery> {

    private final HotelRepository hotels;

    public ViewAllHotelsQueryHandler(HotelRepository hotels) {
        this.hotels = hotels;
    }


    @Override
    public Collection<Hotel> handle(ViewAllHotelsQuery query) {
        SearchHotelParam params = new SearchHotelParam(query.name, query.categories, query.services);
        return hotels.search(params);
    }
}
