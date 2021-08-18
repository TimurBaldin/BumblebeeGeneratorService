package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.generators.GeneratorInformation;
import com.rufus.bumblebee.generators.annotation.AnnotationHandler;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import com.rufus.bumblebee.services.exceptions.GeneratorCreatingException;
import com.rufus.bumblebee.services.interfaces.AsyncGeneratorService;
import com.rufus.bumblebee.services.interfaces.GeneratorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class : Сервис создания тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class GeneratorServiceImpl implements GeneratorService<GeneratorsRequest, String> {

    private final AnnotationHandler handler;
    private final ContainerRepository containerRepository;

    private final AsyncGeneratorService asyncGeneratorService;

    public GeneratorServiceImpl(AnnotationHandler handler, ContainerRepository containerRepository, AsyncGeneratorService asyncGeneratorService) {
        this.handler = handler;
        this.containerRepository = containerRepository;
        this.asyncGeneratorService = asyncGeneratorService;
    }

    public String addGenerators(GeneratorsRequest request) throws Exception {
        Container container = containerRepository.getContainerByCuid(request.getCuid());
        if ((container.getStatus() != ContainerStatus.NEW)) {
            throw new GeneratorCreatingException("It is not possible to add generators to a container that is not in the NEW status");
        }
        List<BaseGenerator> generators = new ArrayList<>(request.getGeneratorInfo().size());

        for (GeneratorInformation information : request.getGeneratorInfo()) {
            BaseGenerator generator = (BaseGenerator) handler.getBeanByName(information.getGeneratorName());
            handler.setParameters(generator.getClass().getFields(), information.getValues(), generator);
            generators.add(generator);
        }
        container.setStatus(ContainerStatus.PREPARATION_FOR_GENERATION);
        asyncGeneratorService.asyncGenerateTestData(generators, containerRepository.save(container));
        return container.getCuid().toString();
    }
}
