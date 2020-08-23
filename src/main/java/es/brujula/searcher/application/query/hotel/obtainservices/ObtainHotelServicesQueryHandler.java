package es.brujula.searcher.application.query.hotel.obtainservices;

import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.shared.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class ObtainHotelServicesQueryHandler implements QueryHandler<Collection<Services>, ObtainHotelServicesQuery> {

    private ServiceRepository services;

    public ObtainHotelServicesQueryHandler(ServiceRepository services) {
        this.services = services;
    }

    @Override
    public Collection<Services> handle(ObtainHotelServicesQuery query) {
        String id = query.hotelId();

        return services.byIdHotel(id);
    }
}
