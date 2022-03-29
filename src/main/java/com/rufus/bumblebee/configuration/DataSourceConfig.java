package com.rufus.bumblebee.configuration;

import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.CustomContainerRepository;
import com.rufus.bumblebee.repository.TestDataRepository;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {
        ContainerRepository.class,
        CustomContainerRepository.class,
        TestDataRepository.class
})
public class DataSourceConfig {

    private final Environment env;

    private static final String HIBERNATE_DDL_PROPERTY = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
    private static final String HIBERNATE_FORMAT_SQL_PROPERTY = "hibernate.format_sql";
    private static final String HIBERNATE_BATCH_SIZE_PROPERTY = "hibernate.jdbc.batch_size";
    private static final String HIBERNATE_ORDER_INSERTS_PROPERTY = "hibernate.order_inserts";
    private static final String HIBERNATE_STATISTICS_PROPERTY = "hibernate.generate_statistics";
    private static final String HIBERNATE_METADATA_PROPERTY = "hibernate.temp.use_jdbc_metadata_defaults";

    @Autowired
    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPersistenceUnitName("dataGenerator");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Autowired LocalContainerEntityManagerFactoryBean factoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factoryBean.getObject());
        return transactionManager;
    }

    @Bean
    public SpringLiquibase liquibase(@Autowired DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog.yaml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    private Properties additionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty(HIBERNATE_DDL_PROPERTY, env.getProperty(HIBERNATE_DDL_PROPERTY));
        properties.setProperty(HIBERNATE_DIALECT_PROPERTY, env.getProperty(HIBERNATE_DIALECT_PROPERTY));
        properties.setProperty(HIBERNATE_SHOW_SQL_PROPERTY, env.getProperty(HIBERNATE_SHOW_SQL_PROPERTY));
        properties.setProperty(HIBERNATE_FORMAT_SQL_PROPERTY, env.getProperty(HIBERNATE_FORMAT_SQL_PROPERTY));
        properties.setProperty(HIBERNATE_BATCH_SIZE_PROPERTY, env.getProperty(HIBERNATE_BATCH_SIZE_PROPERTY));
        properties.setProperty(HIBERNATE_ORDER_INSERTS_PROPERTY, env.getProperty(HIBERNATE_ORDER_INSERTS_PROPERTY));
        properties.setProperty(HIBERNATE_STATISTICS_PROPERTY, env.getProperty(HIBERNATE_STATISTICS_PROPERTY));
        properties.setProperty(HIBERNATE_METADATA_PROPERTY, env.getProperty(HIBERNATE_METADATA_PROPERTY));
        return properties;
    }
}
