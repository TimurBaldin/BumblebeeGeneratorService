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
import java.util.List;

@Service
public class AsyncGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(AsyncGeneratorService.class);

    private final TestDataRepository repository;
    private final ContainerRepository containerRepository;

    public AsyncGeneratorService(TestDataRepository repository, ContainerRepository containerRepository) {
        this.repository = repository;
        this.containerRepository = containerRepository;
    }

    @Async("threadPoolTaskExecutor")
    public void asyncGenerateTestData(List<BaseGenerator> generators, Container container) {
        log.info("AsyncGeneratorService started");
        generators.forEach(s -> repository.saveTestData(s.build(), container.getId()));
        container.setStatus(ContainerStatus.GENERATION_COMPLETED);
        container.setUpdateDate(LocalDateTime.now());
        containerRepository.createOrUpdateContainer(container);
        log.info("AsyncGeneratorService finished");
    }
}
