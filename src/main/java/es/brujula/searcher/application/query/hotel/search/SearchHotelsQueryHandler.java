package es.brujula.searcher.application.query.hotel.search;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SearchHotelsQueryHandler implements QueryHandler<Collection<Hotel>, SearchHotelsQuery> {

    private final HotelRepository hotels;

    public SearchHotelsQueryHandler(HotelRepository hotels) {
        this.hotels = hotels;
    }

    @Override
    public Collection<Hotel> handle(SearchHotelsQuery query) {
        SearchHotelParam params = new SearchHotelParam(query.name, query.categories, query.services);
        return hotels.search(params);
    }
}
