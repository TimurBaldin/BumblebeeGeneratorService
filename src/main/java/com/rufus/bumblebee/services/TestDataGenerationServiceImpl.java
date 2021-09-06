package com.rufus.bumblebee.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.TestDataRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.TestData;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import com.rufus.bumblebee.services.dto.TestDataDto;
import com.rufus.bumblebee.services.interfaces.KafkaService;
import com.rufus.bumblebee.services.interfaces.TestDataGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@Service
public class TestDataGenerationServiceImpl implements TestDataGenerationService {

    private static final Logger log = LoggerFactory.getLogger(TestDataGenerationServiceImpl.class);

    private final TestDataRepository repository;
    private final ContainerRepository containerRepository;
    private final KafkaService<List<TestDataDto>> kafkaService;

    @Autowired
    public TestDataGenerationServiceImpl(TestDataRepository repository, ContainerRepository containerRepository, KafkaService<List<TestDataDto>> kafkaService) {
        this.repository = repository;
        this.containerRepository = containerRepository;
        this.kafkaService = kafkaService;
    }


    public void generateTestData(List<BaseGenerator> generators, Container container, SseEmitter emitter) throws IOException {
        String cuid = container.getCuid().toString();
        emitter.send(event()
                .data("The task of generating test data is started", TEXT_PLAIN)
                .id(cuid)
                .name(LocalDateTime.now().toString()));

        List<TestDataDto> dto = mapToDto(generators);

        kafkaService.sendTestDataToReportService(dto, container);
        emitter.send(event().data("The test data has been sent to kafka")
                .id(cuid)
                .name(LocalDateTime.now().toString()));

        if (container.getHistoryOn()) {
            try {
                repository.saveAll(mapFromDto(dto, container.getId()));
                emitter.send(event().data("The test data has been save to bd", TEXT_PLAIN)
                        .id(cuid)
                        .name(LocalDateTime.now().toString()));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
                containerUpdateStatus(ContainerStatus.GENERATION_ERROR, container);
                emitter.completeWithError(e);
                return;
            }
        }
        containerUpdateStatus(ContainerStatus.GENERATION_COMPLETED, container);
        emitter.send(event().data("The task of generating test data is finished", TEXT_PLAIN)
                .id(cuid)
                .name(LocalDateTime.now().toString()));
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

    private void containerUpdateStatus(ContainerStatus status, Container container) {
        container.setStatus(status);
        container.setUpdateDate(LocalDateTime.now());
        containerRepository.save(container);
    }
}
