package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.query.hotel.search.SearchHotelsQuery;
import es.brujula.searcher.application.query.hotel.search.SearchHotelsQueryHandler;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.infrastructure.ui.rest.QueryController;
import es.brujula.shared.QueryHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/hotels")
final class SearchHotelsPage extends QueryController<Collection<Hotel>> {

    private final QueryHandler<Collection<Hotel>, SearchHotelsQuery> queryHandler;

    public SearchHotelsPage(SearchHotelsQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Collection<Hotel>>> getHotels(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "category", required = false) List<String> categories, @RequestParam(name = "service", required = false) List<String> services) {
        SearchHotelsQuery query = new SearchHotelsQuery(name, categories, services);

        Map<String, Collection<Hotel>> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
