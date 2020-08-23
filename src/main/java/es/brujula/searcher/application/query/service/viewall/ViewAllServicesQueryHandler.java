package es.brujula.searcher.application.query.service.viewall;

import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class ViewAllServicesQueryHandler implements QueryHandler<Collection<Services>, ViewAllServicesQuery> {

    private final ServiceRepository services;

    public ViewAllServicesQueryHandler(ServiceRepository services) {
        this.services = services;
    }


    @Override
    public Collection<Services> handle(ViewAllServicesQuery query) {
        return services.all();
    }
}
