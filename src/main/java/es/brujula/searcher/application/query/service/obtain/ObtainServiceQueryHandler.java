package es.brujula.searcher.application.query.service.obtain;

import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public final class ObtainServiceQueryHandler implements QueryHandler<Services, ObtainServiceQuery> {

    private ServiceRepository services;

    public ObtainServiceQueryHandler(ServiceRepository services) {
        this.services = services;
    }

    @Override
    public Services handle(ObtainServiceQuery query) {
        String id = query.serviceId();

        return services.byIdOrFail(id);
    }
}
