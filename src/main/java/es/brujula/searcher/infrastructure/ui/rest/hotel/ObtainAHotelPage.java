package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.query.hotel.obtain.ObtainHotelQuery;
import es.brujula.searcher.application.query.hotel.obtain.ObtainHotelQueryHandler;
import es.brujula.searcher.application.query.room.viewall.ViewAllRoomsQuery;
import es.brujula.searcher.application.query.service.viewall.ViewAllServicesQuery;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.room.model.Room;
import es.brujula.searcher.domain.service.model.Services;
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

import java.util.Collection;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/hotels")
final class ObtainAHotelPage extends QueryController<HotelResponse> {

    private final QueryHandler<Hotel, ObtainHotelQuery> queryHandler;
    private final QueryHandler<Collection<Room>, ViewAllRoomsQuery> roomsQueryHandler;
    private final QueryHandler<Collection<Services>, ViewAllServicesQuery> servicesQueryHandler;

    public ObtainAHotelPage(ObtainHotelQueryHandler queryHandler,
                            QueryHandler<Collection<Room>, ViewAllRoomsQuery> roomsQueryHandler,
                            QueryHandler<Collection<Services>, ViewAllServicesQuery> servicesQueryHandler) {
        this.queryHandler = queryHandler;
        this.roomsQueryHandler = roomsQueryHandler;
        this.servicesQueryHandler = servicesQueryHandler;
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
        ViewAllServicesQuery viewAllServicesQuery = new ViewAllServicesQuery(id);
        Collection<Services> services = servicesQueryHandler.handle(viewAllServicesQuery);
        if (services.size() > 0) {
            Link ordersLink = linkTo(methodOn(ViewAllHotelServicesPage.class)
                    .getServices(id)).withRel("services");
            hotelResponse.add(ordersLink);
        }
    }

    private void addRoomsLink(String id, HotelResponse hotelResponse) {
        ViewAllRoomsQuery viewAllRoomsQuery = new ViewAllRoomsQuery(id);
        Collection<Room> handle = roomsQueryHandler.handle(viewAllRoomsQuery);
        if (handle.size() > 0) {
            Link ordersLink = linkTo(methodOn(ViewAllRoomsPage.class)
                    .getRooms(id)).withRel("rooms");
            hotelResponse.add(ordersLink);
        }
    }
}
