package es.brujula.searcher.infrastructure.ui.rest.room;

import es.brujula.searcher.application.query.room.obtain.ObtainRoomQuery;
import es.brujula.searcher.application.query.room.obtain.ObtainRoomQueryHandler;
import es.brujula.searcher.domain.room.model.Room;
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
@RequestMapping("/v1/rooms")
final class ObtainARoomPage extends QueryController<Room> {

    private final QueryHandler<Room, ObtainRoomQuery> queryHandler;

    public ObtainARoomPage(ObtainRoomQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Room>> index(@PathVariable String id) {
        ObtainRoomQuery query = new ObtainRoomQuery(id);
        Map<String, Room> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
