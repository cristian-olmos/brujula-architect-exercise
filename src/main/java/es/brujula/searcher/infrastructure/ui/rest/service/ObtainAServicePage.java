package es.brujula.searcher.infrastructure.ui.rest.service;

import es.brujula.searcher.application.query.service.obtain.ObtainServiceQuery;
import es.brujula.searcher.application.query.service.obtain.ObtainServiceQueryHandler;
import es.brujula.searcher.domain.service.model.Services;
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
@RequestMapping("/v1/services")
final class ObtainAServicePage extends QueryController<Services> {

    private final QueryHandler<Services, ObtainServiceQuery> queryHandler;

    public ObtainAServicePage(ObtainServiceQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Services>> index(@PathVariable String id) {
        ObtainServiceQuery query = new ObtainServiceQuery(id);
        Map<String, Services> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
