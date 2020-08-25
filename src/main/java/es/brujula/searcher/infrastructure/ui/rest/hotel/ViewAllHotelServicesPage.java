package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.query.hotel.obtainhotelservices.ObtainHotelServicesQuery;
import es.brujula.searcher.application.query.hotel.obtainhotelservices.ObtainHotelServicesQueryHandler;
import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.infrastructure.ui.rest.QueryController;
import es.brujula.shared.QueryHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/v1/hotels/{hotelId}/services")
public class ViewAllHotelServicesPage extends QueryController<Collection<Services>> {

    private final QueryHandler<Collection<Services>, ObtainHotelServicesQuery> queryHandler;

    public ViewAllHotelServicesPage(ObtainHotelServicesQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Collection<Services>>> getServices(@PathVariable String hotelId) {
        ObtainHotelServicesQuery query = new ObtainHotelServicesQuery(hotelId);

        Map<String, Collection<Services>> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
