package com.rufus.bumblebee.services;

import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.repository.TestDataRepository;
import com.rufus.bumblebee.repository.tables.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

@Service
public class AsyncGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(AsyncGeneratorService.class);

    private final TestDataRepository repository;
    private final ContainerRepository containerRepository;
    private final KafkaService kafkaService;

    public AsyncGeneratorService(TestDataRepository repository, ContainerRepository containerRepository, KafkaService kafkaService) {
        this.repository = repository;
        this.containerRepository = containerRepository;
        this.kafkaService = kafkaService;
    }

    @Async("threadPoolTaskExecutor")
    public void asyncGenerateTestData(List<BaseGenerator> generators, Container container) {
        log.info("AsyncGeneratorService started");
        List<Map<String, List<String>>> data = buildData(generators);
        if (container.getAuthenticated()) {
            repository.saveTestData(data, container.getId());
        }
        kafkaService.sendTestDataToReportService(data, container);

        container.setStatus(ContainerStatus.GENERATION_COMPLETED);
        container.setUpdateDate(LocalDateTime.now());
        containerRepository.createOrUpdateContainer(container);
        log.info("AsyncGeneratorService finished");
    }

    private List<Map<String, List<String>>> buildData(List<BaseGenerator> generators) {
        List<Map<String, List<String>>> data = new ArrayList<>();
        generators.forEach(g -> data.add(singletonMap(g.getGeneratorName(), g.build())));
        return data;
    }
}
