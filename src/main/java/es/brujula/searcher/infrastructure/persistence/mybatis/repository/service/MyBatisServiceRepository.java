package es.brujula.searcher.infrastructure.persistence.mybatis.repository.service;

import es.brujula.searcher.domain.service.exception.ServiceNotFoundException;
import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.searcher.infrastructure.persistence.mybatis.mapper.service.ServiceMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class MyBatisServiceRepository implements ServiceRepository {

    private ServiceMapper mapper;

    public MyBatisServiceRepository(ServiceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Services byIdOrFail(String roomId) {

        return this.byId(roomId)
                .orElseThrow(() -> new ServiceNotFoundException(roomId));
    }


    @Override
    public Optional<Services> byId(String id) {
        return this.mapper.byId(id);
    }

    @Override
    public void add(Services room) {
        this.mapper.add(room);
    }

    @Override
    public void delete(String id) {
        this.mapper.delete(id);
    }

    @Override
    public Collection<Services> all() {
        return this.mapper.all();
    }

}
