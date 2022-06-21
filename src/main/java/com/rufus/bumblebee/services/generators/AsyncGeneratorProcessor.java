package com.rufus.bumblebee.services.generators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.TestDataRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.TestData;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import com.rufus.bumblebee.services.dto.Pair;
import com.rufus.bumblebee.services.dto.TestDataDto;
import com.rufus.bumblebee.services.interfaces.GeneratorProcessor;
import com.rufus.bumblebee.services.interfaces.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsyncGeneratorProcessor implements GeneratorProcessor {
    private static final Logger log = LoggerFactory.getLogger(AsyncGeneratorProcessor.class);
    private TestDataRepository repository;
    private KafkaService<List<TestDataDto>> kafkaService;
    private final ContainerRepository containerRepository;

    @Autowired
    public AsyncGeneratorProcessor(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }


    @Async
    public void generateTestData(List<Pair> generators, Container container) {
        List<TestDataDto> dto = mapToDto(generators);
        kafkaService.sendTestData(dto, container);

        if (container.getHistoryOn()) {
            try {
                repository.saveAll(mapFromDto(dto, container.getId()));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
                containerUpdateStatus(ContainerStatus.GENERATION_ERROR, container);
                return;
            }
        }
        containerUpdateStatus(ContainerStatus.GENERATION_COMPLETED, container);
    }

    private List<TestDataDto> mapToDto(List<Pair> pairs) {
        List<TestDataDto> dto = new ArrayList<>(pairs.size());
        pairs.forEach(
                pair -> {
                    dto.add(
                            new TestDataDto(
                                    pair.getDescription().getName(),
                                    pair.getDescription().getGenerator().getTestData(pair.getValues())
                            ));
                });
        return dto;
    }

    private List<TestData> mapFromDto(List<TestDataDto> dtos, Long containerRef) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<TestData> testDataList = new ArrayList<>(dtos.size());
        for (TestDataDto dto : dtos) {
            testDataList.add(
                    new TestData(ow.writeValueAsString(dto.getData()), containerRef, dto.getGeneratorName())
            );
        }
        return testDataList;
    }

    private void containerUpdateStatus(ContainerStatus status, Container container) {
        container.setStatus(status);
        container.setUpdateDate(LocalDateTime.now());
        containerRepository.save(container);
    }

    @Autowired
    public void setRepository(TestDataRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setKafkaService(KafkaService<List<TestDataDto>> kafkaService) {
        this.kafkaService = kafkaService;
    }
}
