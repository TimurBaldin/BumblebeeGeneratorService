package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.generators.GeneratorInformation;
import com.rufus.bumblebee.generators.annotation.AnnotationHandler;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import com.rufus.bumblebee.services.exceptions.AppException;
import com.rufus.bumblebee.services.interfaces.GeneratorService;
import com.rufus.bumblebee.services.interfaces.TestDataGenerationService;
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
public class GeneratorServiceImpl implements GeneratorService<GeneratorsRequest> {

    private final AnnotationHandler handler;
    private final ContainerRepository containerRepository;

    private final TestDataGenerationService testDataGenerationService;

    public GeneratorServiceImpl(AnnotationHandler handler, ContainerRepository containerRepository, TestDataGenerationService testDataGenerationService) {
        this.handler = handler;
        this.containerRepository = containerRepository;
        this.testDataGenerationService = testDataGenerationService;
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void initGenerators(GeneratorsRequest request, SseEmitter emitter) throws Exception {
        emitter.send(SseEmitter.event()
                .data("The task of creating generators has begun")
                .id(String.valueOf(request.getCuid()))
                .name(LocalDateTime.now().toString()));

        validateGeneratorsRequest(request);
        Container container = containerRepository.getContainerByCuid(request.getCuid());
        if ((container.getStatus() != ContainerStatus.GENERATION_COMPLETED)) {
            emitter.completeWithError(new AppException("It is not possible to add generators to a container that is not in the NEW status"));
        }

        List<BaseGenerator> generators = new ArrayList<>(request.getGeneratorInfo().size());
        for (GeneratorInformation information : request.getGeneratorInfo()) {
            BaseGenerator generator = (BaseGenerator) handler.getBeanByName(information.getGeneratorName());
            handler.setParameters(generator.getClass().getFields(), information.getValues(), generator);
            generators.add(generator);
        }

        container.setStatus(ContainerStatus.PREPARATION_FOR_GENERATION);
        emitter.send(SseEmitter.event()
                .data("The creation of the generators is completed, the transition to the generation of test data")
                .id(String.valueOf(request.getCuid()))
                .name("sse event, date:" + LocalDateTime.now()));
        testDataGenerationService.generateTestData(generators, containerRepository.save(container), emitter);

        emitter.send(
                SseEmitter.event()
                        .data("The task of generating test data is completed")
                        .id(String.valueOf(request.getCuid()))
                        .name(LocalDateTime.now().toString())
        );
        emitter.complete();
    }
}
