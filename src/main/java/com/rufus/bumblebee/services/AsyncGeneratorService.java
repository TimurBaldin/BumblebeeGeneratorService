package com.rufus.bumblebee.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.TestData;
import com.rufus.bumblebee.repositoryV2.TestDataRepository;
import com.rufus.bumblebee.services.dto.TestDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsyncGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(AsyncGeneratorService.class);

    private final TestDataRepository repository;
    private final ContainerRepository containerRepository;
    private final KafkaService kafkaService;

    @Autowired
    public AsyncGeneratorService(TestDataRepository repository, ContainerRepository containerRepository, KafkaService kafkaService) {
        this.repository = repository;
        this.containerRepository = containerRepository;
        this.kafkaService = kafkaService;
    }

    @Async("threadPoolTaskExecutor")
    public void asyncGenerateTestData(List<BaseGenerator> generators, Container container) throws JsonProcessingException {
        log.info("AsyncGeneratorService started");
        List<TestDataDto> dto = mapToDto(generators);

        kafkaService.sendTestDataToReportService(dto, container);

        if (container.getAuthenticated()) {
            repository.saveAll(mapFromDto(dto, container.getId()));
        }

        container.setStatus(ContainerStatus.GENERATION_COMPLETED);
        container.setUpdateDate(LocalDateTime.now());
        containerRepository.createOrUpdateContainer(container);
        log.info("AsyncGeneratorService finished");
    }


    private List<TestDataDto> mapToDto(List<BaseGenerator> generators) {
        List<TestDataDto> dto = new ArrayList<>(generators.size());
        generators.forEach(
                g -> dto.add(new TestDataDto(g.getGeneratorName(), g.build()))
        );
        return dto;
    }

    private List<TestData> mapFromDto(List<TestDataDto> dtos, Long containerRef) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<TestData> testDataList = new ArrayList<>(dtos.size());
        for (TestDataDto dto : dtos) {
            testDataList.add(new TestData(
                    ow.writeValueAsString(dto.getData()), containerRef, dto.getGeneratorName())
            );
        }
        return testDataList;
    }
}
