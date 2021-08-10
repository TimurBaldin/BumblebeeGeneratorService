package com.rufus.bumblebee.repository.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.repository.TestDataRepository;
import com.rufus.bumblebee.repository.tables.Container;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

@RunWith(SpringRunner.class)
@TestPropertySource(properties =
        {"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration",})
@ContextConfiguration(classes = ConfigurationRepository.class)
@Ignore
public class ConfigurationRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Bean("dataSource")
    public DataSource dataSource() throws IOException, SQLException {

        EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.builder().setPort(1890).start();
        DataSource dataSource = embeddedPostgres.getPostgresDatabase();
        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(FileUtils.readFileToString(new File("src/test/resources/init.sql"), "utf-8"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.rufus.bumblebee.repository");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(@Autowired LocalContainerEntityManagerFactoryBean factoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factoryBean.getObject());
        return transactionManager;
    }

    @Bean
    public ContainerRepository containerRepository() {
        return new ContainerRepository();
    }

    @Bean
    public TestDataRepository testDataRepository() {
        return new TestDataRepository();
    }

    protected Container getTestContainer() {
        Container container = new Container();
        container.setName("test");
        container.setAuthenticated(false);
        container.setDate(LocalDateTime.now());
        container.setStatus(ContainerStatus.NEW);
        container.setType(ReportType.EXCEL_TYPE);
        container.setCuid(UUID.randomUUID());
        return container;
    }

    private Properties additionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("connection_pool_size", "100");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        return properties;
    }
}
