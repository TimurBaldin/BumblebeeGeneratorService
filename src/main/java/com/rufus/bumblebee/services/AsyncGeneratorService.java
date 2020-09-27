package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.TestDataRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.ContainerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncGeneratorService {

    private static Logger log = LoggerFactory.getLogger(AsyncGeneratorService.class);

    @Autowired
    private TestDataRepository repository;
    @Autowired
    private ContainerRepository containerRepository;

    @Async("threadPoolTaskExecutor")
    public void asyncGenerateTestData(List<BaseGenerator> generators, Container container) throws TransferException {
        log.info("AsyncGeneratorService started");
        for (BaseGenerator generator : generators) {
            generator.construct();
            repository.saveTestData(generator.getTestData(), container.getId());
        }
        container.setStatus(ContainerStatus.GENERATION_COMPLETED);
        containerRepository.createOrUpdateContainer(container);
        log.info("AsyncGeneratorService finished");
    }
}
