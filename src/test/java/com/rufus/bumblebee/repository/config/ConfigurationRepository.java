package com.rufus.bumblebee.repository.config;

import com.rufus.bumblebee.configuration.DataSourceConfig;
import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataSourceConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Ignore
public class ConfigurationRepository {

    protected Container getTestContainer() {
        Container container = new Container();
        container.setName(UUID.randomUUID().toString());
        container.setHistoryOn(false);
        container.setDate(LocalDateTime.now());
        container.setStatus(ContainerStatus.NEW);
        container.setType(ReportType.EXCEL_TYPE);
        container.setCuid(UUID.randomUUID());
        return container;
    }
}
