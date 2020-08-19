package es.brujula.searcher.infrastructure.config.persistence;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring")
public class PersistenceProperties {

    private DataSourceProperties dataSource;

    public DataSourceProperties getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceProperties dataSource) {
        this.dataSource = dataSource;
    }
}
