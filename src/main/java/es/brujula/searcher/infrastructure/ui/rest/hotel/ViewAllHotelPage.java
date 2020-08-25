package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.query.hotel.viewall.ViewAllHotelsQuery;
import es.brujula.searcher.application.query.hotel.viewall.ViewAllHotelsQueryHandler;
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
final class ViewAllHotelPage extends QueryController<Collection<Hotel>> {

    private final QueryHandler<Collection<Hotel>, ViewAllHotelsQuery> queryHandler;

    public ViewAllHotelPage(ViewAllHotelsQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Collection<Hotel>>> getHotels(@RequestParam(name = "name") String name, @RequestParam(name = "categories") List<String> categories, @RequestParam(name = "services") List<String> services) {
        ViewAllHotelsQuery query = new ViewAllHotelsQuery(name, categories, services);

        Map<String, Collection<Hotel>> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
