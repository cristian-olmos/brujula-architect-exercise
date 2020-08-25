package es.brujula.searcher.application.query.hotel.viewservices;

import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class ViewHotelServicesQueryHandler implements QueryHandler<Collection<Services>, ViewHotelServicesQuery> {

    private final ServiceRepository services;

    public ViewHotelServicesQueryHandler(ServiceRepository services) {
        this.services = services;
    }


    @Override
    public Collection<Services> handle(ViewHotelServicesQuery query) {
        return services.all();
    }
}
