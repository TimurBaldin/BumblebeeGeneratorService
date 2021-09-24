package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.GeneratorRequest;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.generators.GeneratorInformation;
import com.rufus.bumblebee.generators.annotation.GeneratorAnnotationHandler;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import com.rufus.bumblebee.services.interfaces.GeneratorService;
import com.rufus.bumblebee.services.interfaces.TestDataGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.rufus.bumblebee.utils.GeneratorValidatorUtils.validateGeneratorsRequest;

/**
 * Class : Сервис создания тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class GeneratorServiceImpl implements GeneratorService<GeneratorRequest> {

    private static final Logger log = LoggerFactory.getLogger(GeneratorServiceImpl.class);

    private final GeneratorAnnotationHandler handler;
    private final ContainerRepository containerRepository;
    private final TestDataGenerationService testDataGenerationService;

    public GeneratorServiceImpl(GeneratorAnnotationHandler handler, ContainerRepository containerRepository, TestDataGenerationService testDataGenerationService) {
        this.handler = handler;
        this.containerRepository = containerRepository;
        this.testDataGenerationService = testDataGenerationService;
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void initGenerators(GeneratorRequest request, SseEmitter emitter) throws Exception {
        try {
            validateGeneratorsRequest(request);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            emitter.completeWithError(ex);
            return;
        }
        String cuid = request.getCuid();
        emitter.send(SseEmitter.event()
                .data("The task of creating generators has begun", MediaType.TEXT_PLAIN)
                .id(cuid)
                .name(LocalDateTime.now().toString()));

        Container container = containerRepository.getContainerByCuid(cuid);
        if (container.getStatus() != ContainerStatus.NEW) {
            emitter.completeWithError(
                    new Exception("It is not possible to add generators to a container that is not in the NEW status")
            );
            return;
        }
        List<BaseGenerator> generators = new ArrayList<>(request.getGeneratorInfo().size());
        for (GeneratorInformation information : request.getGeneratorInfo()) {
            BaseGenerator generator = (BaseGenerator) handler.getGeneratorByName(information.getGeneratorName());
            try {
                handler.setParameters(generator.getClass().getFields(), information.getValues(), generator);
            } catch (Exception exception) {
                emitter.completeWithError(exception);
            }
            generators.add(generator);
        }

        container.setStatus(ContainerStatus.PREPARATION_FOR_GENERATION);
        emitter.send(SseEmitter.event()
                .data("The creation of the generators is completed, starting the process generation of test data", MediaType.TEXT_PLAIN)
                .id(cuid)
                .name(LocalDateTime.now().toString()));

        testDataGenerationService.generateTestData(generators, containerRepository.save(container), emitter);
        emitter.send(
                SseEmitter.event()
                        .data("The task of generating test data is completed", MediaType.TEXT_PLAIN)
                        .id(cuid)
                        .name(LocalDateTime.now().toString())
        );
        emitter.complete();
    }
}
