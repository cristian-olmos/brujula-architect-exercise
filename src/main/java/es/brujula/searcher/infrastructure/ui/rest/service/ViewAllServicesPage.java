package es.brujula.searcher.infrastructure.ui.rest.service;

import es.brujula.searcher.application.query.service.viewall.ViewAllServicesQuery;
import es.brujula.searcher.application.query.service.viewall.ViewAllServicesQueryHandler;
import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.infrastructure.ui.rest.QueryController;
import es.brujula.shared.QueryHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/v1/services")
final class ViewAllServicesPage extends QueryController<Collection<Services>> {

    private final QueryHandler<Collection<Services>, ViewAllServicesQuery> queryHandler;

    public ViewAllServicesPage(ViewAllServicesQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Collection<Services>>> index() {
        ViewAllServicesQuery query = new ViewAllServicesQuery();

        Map<String, Collection<Services>> response = this.createResponse(this.queryHandler.handle(query));

        return ResponseEntity.ok().body(response);
    }
}
