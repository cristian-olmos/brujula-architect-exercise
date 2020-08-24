package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.query.hotel.obtain.ObtainHotelQuery;
import es.brujula.searcher.application.query.hotel.obtain.ObtainHotelQueryHandler;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.infrastructure.ui.rest.QueryController;
import es.brujula.searcher.infrastructure.ui.rest.hotel.dto.HotelResponse;
import es.brujula.searcher.infrastructure.ui.rest.room.ViewAllRoomsPage;
import es.brujula.shared.QueryHandler;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/hotels")
final class ObtainAHotelPage extends QueryController<HotelResponse> {

    private final QueryHandler<Hotel, ObtainHotelQuery> queryHandler;

    public ObtainAHotelPage(ObtainHotelQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, HotelResponse>> index(@PathVariable String id) {
        ObtainHotelQuery query = new ObtainHotelQuery(id);

        HotelResponse hotelResponse = HotelResponse.fromDomain(this.queryHandler.handle(query));
        addRoomsLink(id, hotelResponse);
        addServicesLink(id, hotelResponse);

        Map<String, HotelResponse> response = this.createResponse(hotelResponse);

        return ResponseEntity.ok().body(response);
    }

    private void addServicesLink(String id, HotelResponse hotelResponse) {
        Link ordersLink = linkTo(methodOn(ViewAllHotelServicesPage.class)
                .getServices(id)).withRel("services");
        hotelResponse.add(ordersLink);
    }

    private void addRoomsLink(String id, HotelResponse hotelResponse) {
        Link ordersLink = linkTo(methodOn(ViewAllRoomsPage.class)
                .getRooms(id)).withRel("rooms");
        hotelResponse.add(ordersLink);
    }
}
