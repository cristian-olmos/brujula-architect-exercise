package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.query.service.viewall.ViewAllServicesQuery;
import es.brujula.searcher.application.query.service.viewall.ViewAllServicesQueryHandler;
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

    private final QueryHandler<Collection<Services>, ViewAllServicesQuery> queryHandler;

    public ViewAllHotelServicesPage(ViewAllServicesQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Collection<Services>>> getServices(@PathVariable String hotelId) {
        ViewAllServicesQuery query = new ViewAllServicesQuery(hotelId);

        Map<String, Collection<Services>> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
