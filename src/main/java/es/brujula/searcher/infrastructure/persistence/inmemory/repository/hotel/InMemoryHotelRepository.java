package es.brujula.searcher.infrastructure.persistence.inmemory.repository.hotel;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.hotel.exception.HotelNotFoundException;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryHotelRepository implements HotelRepository {

    private static final String FILTER_BY_NAME = "name";
    private static final String FILTER_BY_GMS = "gmsCode";
    private static final String FILTER_BY_SEASON = "season";
    private static final String FILTER_BY_TOTAL_PLACES = "total";
    private static final String FILTER_BY_OFFERED_PLACES = "offered";

    private Collection<Hotel> hotels;
    private Map<String, Hotel> hotelsById;

    public InMemoryHotelRepository() {
        this.hotels = new ArrayList<>();
        this.hotelsById = new HashMap<>();
    }

    public InMemoryHotelRepository(Collection<Hotel> hotels) {
        this.hotels = new ArrayList<>();
        this.hotelsById = new HashMap<>();
        hotels.forEach(this::add);
    }

    @Override
    public void add(Hotel hotel) {
        this.hotels.add(hotel);
        this.hotelsById.put(hotel.id(), hotel);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Collection<Hotel> all() {
        return null;
    }

    @Override
    public void save(Hotel hotel) {

    }

    @Override
    public Collection<Hotel> search(PaginatedSearchParam searchParam) {

        Stream<Hotel> changesFiltered = this.applyFilters(searchParam.filters(), this.hotels.stream());

        return changesFiltered
                .collect(Collectors.toList());
    }

    public Stream<Hotel> applyFilters(Map<String, String> filters, Stream<Hotel> changesFiltered) {

//        for (Map.Entry<String, String> entry : filters.entrySet()) {
//            String filterKey = entry.getKey().trim();
//            String filterValue = filters.get(filterKey).trim();
//
//            switch (filterKey) {
//                case FILTER_BY_NAME:
//                    changesFiltered = changesFiltered.filter(hotel -> filterValue.equalsIgnoreCase(hotel.name()));
//                    break;
//                case FILTER_BY_SEASON:
//                    changesFiltered = changesFiltered.filter(hotel -> Long.parseLong(filterValue) == hotel.season().fromYear());
//                    break;
//                case FILTER_BY_GMS:
//                    changesFiltered = changesFiltered.filter(hotel -> filterValue.equalsIgnoreCase(hotel.gmsCode()));
//                    break;
//                case FILTER_BY_TOTAL_PLACES:
//                    changesFiltered = changesFiltered.filter(hotel -> Long.parseLong(filterValue) <= hotel.places().total());
//                    break;
//                case FILTER_BY_OFFERED_PLACES:
//                    changesFiltered = changesFiltered.filter(hotel -> Long.parseLong(filterValue) <= hotel.places().offered());
//                    break;
//                default:
//                    throw new IllegalArgumentException();
//            }
//        }

        return changesFiltered;
    }

    @Override
    public Integer count(PaginatedSearchParam searchParam) {
        return this.hotels.size();
    }

    @Override
    public Optional<Hotel> byId(String hotelId) {
        return Optional.ofNullable(hotelsById.get(hotelId));
    }

    @Override
    public Hotel byIdOrFail(String hotelId) {

        Hotel hotel = this.byId(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(hotelId));

        return Hotel.create(
                hotel.id(),
                hotel.name(),
                hotel.category(),
                hotel.address()
        );
    }
}
