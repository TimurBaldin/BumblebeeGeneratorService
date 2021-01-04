package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.controllers.responses.GeneratorDto;
import com.rufus.bumblebee.controllers.responses.GeneratorParametersDto;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.generators.GeneratorInformation;
import com.rufus.bumblebee.generators.annotation.AnnotationHandler;
import com.rufus.bumblebee.generators.annotation.GeneratorDescription;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.repository.tables.Container;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class : Сервис создания тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class GeneratorService {

    private final AnnotationHandler handler;
    private final ContainerRepository containerRepository;

    private final AsyncGeneratorService asyncGeneratorService;

    public GeneratorService(AnnotationHandler handler, ContainerRepository containerRepository, AsyncGeneratorService asyncGeneratorService) {
        this.handler = handler;
        this.containerRepository = containerRepository;
        this.asyncGeneratorService = asyncGeneratorService;
    }


    public void addGenerators(GeneratorsRequest request) throws NotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Container container = containerRepository.getContainerById(request.getContainerId());

        List<BaseGenerator> generators = new ArrayList<>(request.getGeneratorInfo().size());

        for (GeneratorInformation information : request.getGeneratorInfo()) {
            BaseGenerator generator = (BaseGenerator) handler.getBeanByName(information.getGeneratorName());
            handler.setParameters(generator.getClass().getDeclaredFields(), information.getValues(), generator);
            generators.add(generator);
        }
        container.setStatus(ContainerStatus.PREPARATION_FOR_GENERATION);
        asyncGeneratorService.asyncGenerateTestData(generators, containerRepository.createOrUpdateContainer(container));
    }

    public List<GeneratorDto> getGeneratorsInformation() {
        List<GeneratorDto> generatorDtos = new ArrayList<>();
        Map<GeneratorDescription, List<GeneratorParameter>> map = AnnotationHandler.getGeneratorBeans();
        for (Map.Entry<GeneratorDescription, List<GeneratorParameter>> entry : map.entrySet()) {
            generatorDtos.add(new GeneratorDto(
                    entry.getKey().generatorName(),
                    entry.getKey().description(),
                    entry.getValue().stream()
                            .map(s -> new GeneratorParametersDto(s.name(), s.description()))
                            .collect(Collectors.toList()))
            );
        }
        return generatorDtos;
    }
}
