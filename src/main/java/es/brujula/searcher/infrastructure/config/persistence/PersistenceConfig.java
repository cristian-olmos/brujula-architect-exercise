package es.brujula.searcher.infrastructure.config.persistence;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import(PersistenceProperties.class)
@MapperScan("es.brujula.searcher.infrastructure.persistence.mybatis.mapper")
public class PersistenceConfig {

    private static final String POSTGRESQL_KEY = "PostgreSQL";
    private static final String POSTGRESQL_VALUE = "postgresql";
    private static final String ORACLE_KEY = "Oracle";
    private static final String ORACLE_VALUE = "oracle";

//    @Bean(name = "dataSource")
//    public DataSource dataSource(PersistenceProperties persistenceProperties) throws NamingException {
//        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
//        bean.setJndiName(persistenceProperties.getDataSource().getJndiName());
//        bean.setProxyInterface(DataSource.class);
//        bean.setLookupOnStartup(false);
//        bean.afterPropertiesSet();
//        return (DataSource) bean.getObject();
//    }

    @Bean
    public VendorDatabaseIdProvider vendorDatabaseIdProvider() {
        Properties vendorProperties = new Properties();
        vendorProperties.setProperty(POSTGRESQL_KEY, POSTGRESQL_VALUE);
        vendorProperties.setProperty(ORACLE_KEY, ORACLE_VALUE);
        VendorDatabaseIdProvider dbiIdProvider = new VendorDatabaseIdProvider();
        dbiIdProvider.setProperties(vendorProperties);
        return dbiIdProvider;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,
                                               ApplicationContext applicationContext,
                                               VendorDatabaseIdProvider vendorDatabaseIdProvider) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setDatabaseIdProvider(vendorDatabaseIdProvider);
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return sqlSessionFactory;
    }
}
