package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.tests.TestRequest;
import com.rufus.bumblebee.controllers.responses.GeneratorDto;
import com.rufus.bumblebee.controllers.responses.GeneratorParametersDto;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.generators.GeneratorInformation;
import com.rufus.bumblebee.generators.annotation.AnnotationHandler;
import com.rufus.bumblebee.generators.annotation.GeneratorDescription;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.ContainerStatus;
import com.sun.media.sound.InvalidDataException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
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
@AllArgsConstructor
public class GeneratorService {

    private static Logger log = LoggerFactory.getLogger(GeneratorService.class);

    @Autowired
    private final AnnotationHandler handler;

    @Autowired
    private final ContainerRepository containerRepository;

    @Autowired
    private final AsyncGeneratorService asyncGeneratorService;


    public void addGenerators(TestRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InvalidDataException, TransferException {
        Container container = containerRepository.getContainerById(request.getContainerId());
        List<BaseGenerator> generators = new ArrayList<>();
        for (GeneratorInformation information : request.getGeneratorInformations()) {
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


    @SneakyThrows
    private byte[] convertListToByte(List<BaseGenerator> generators) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(generators);
            return outputStream.toByteArray();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new byte[0];
    }

}
