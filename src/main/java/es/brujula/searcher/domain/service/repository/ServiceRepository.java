package es.brujula.searcher.domain.service.repository;

import es.brujula.searcher.domain.service.model.Services;

import java.util.Collection;
import java.util.Optional;

public interface ServiceRepository {

    Optional<Services> byId(String id);

    Services byIdOrFail(String id);

    void add(Services services);

    void delete(String id);

    Collection<Services> all();

    Collection<Services> byIdHotel(String id);
}
