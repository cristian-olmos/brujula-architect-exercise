package es.brujula.searcher.infrastructure.ui.rest.hotels;

import es.brujula.searcher.application.query.hotel.obtain.ObtainHotelQuery;
import es.brujula.searcher.application.query.hotel.obtain.ObtainHotelQueryHandler;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.infrastructure.ui.rest.QueryController;
import es.brujula.shared.QueryHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/hotels")
final class ObtainAHotelPage extends QueryController<Hotel> {

    private final QueryHandler<Hotel, ObtainHotelQuery> queryHandler;

    public ObtainAHotelPage(ObtainHotelQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Hotel>> index(@PathVariable String id) {
        ObtainHotelQuery query = new ObtainHotelQuery(id);
        Map<String, Hotel> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
