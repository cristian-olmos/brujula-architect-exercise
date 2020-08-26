package es.brujula.searcher.infrastructure.config.persistence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("es.brujula.searcher.infrastructure.persistence.mybatis.mapper")
public class PersistenceConfig {

}
