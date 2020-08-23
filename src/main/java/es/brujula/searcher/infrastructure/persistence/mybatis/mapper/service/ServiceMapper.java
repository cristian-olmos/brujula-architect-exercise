package es.brujula.searcher.infrastructure.persistence.mybatis.mapper.service;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import es.brujula.searcher.domain.service.model.Services;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface ServiceMapper {

    // TODO @SelectProvider
    Collection<Services> search(PaginatedSearchParam searchParam);

    Integer count(PaginatedSearchParam searchParam);

    Optional<Services> byId(@Param("id") String id);

    void add(Services room);

    void delete(String id);

    Collection<Services> all();

}
