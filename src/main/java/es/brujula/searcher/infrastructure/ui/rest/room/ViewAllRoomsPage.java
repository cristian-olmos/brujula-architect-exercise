package es.brujula.searcher.infrastructure.ui.rest.room;

import es.brujula.searcher.application.query.room.viewall.ViewAllRoomsQuery;
import es.brujula.searcher.application.query.room.viewall.ViewAllRoomsQueryHandler;
import es.brujula.searcher.domain.room.model.Room;
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
@RequestMapping("/v1/hotels/{hotelId}/rooms")
final class ViewAllRoomsPage extends QueryController<Collection<Room>> {

    private final QueryHandler<Collection<Room>, ViewAllRoomsQuery> queryHandler;

    public ViewAllRoomsPage(ViewAllRoomsQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Collection<Room>>> index(@PathVariable String hotelId) {
        ViewAllRoomsQuery query = new ViewAllRoomsQuery(hotelId);

        Map<String, Collection<Room>> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
