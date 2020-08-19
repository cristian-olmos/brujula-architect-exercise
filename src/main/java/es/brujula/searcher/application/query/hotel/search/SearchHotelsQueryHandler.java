package es.brujula.searcher.application.query.hotel.search;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.searcher.infrastructure.ui.rest.PaginatedCollection;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class SearchHotelsQueryHandler implements QueryHandler<PaginatedCollection, SearchHotelsQuery> {

    private HotelRepository repository;

    public SearchHotelsQueryHandler(final HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaginatedCollection handle(final SearchHotelsQuery query) {

        PaginatedSearchParam paginatedSearch = this.createPaginatedSearch(query);
        Collection hotels = obtainHotels(paginatedSearch);

        return new PaginatedCollection(
                query.page(),
                query.limit(),
                this.numResults(paginatedSearch),
                hotels
        );
    }

    private Collection<Hotel> obtainHotels(PaginatedSearchParam paginatedSearch) {
        return this.repository.search(paginatedSearch);
    }

    private PaginatedSearchParam createPaginatedSearch(SearchHotelsQuery query) {
        return PaginatedSearchParam.create(
                query.page(),
                query.limit(),
                query.filters(),
                query.sort()
        );
    }

    private Integer numResults(PaginatedSearchParam paginatedSearch) {
        return this.repository.count(paginatedSearch);
    }
}
