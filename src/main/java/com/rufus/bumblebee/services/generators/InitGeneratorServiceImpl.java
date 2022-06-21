package com.rufus.bumblebee.services.generators;

import com.rufus.bumblebee.controllers.requests.GeneratorRequest;
import com.rufus.bumblebee.generators.dto.GeneratorInformation;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.dto.Pair;
import com.rufus.bumblebee.services.interfaces.GeneratorProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rufus.bumblebee.BumblebeeGeneratorService.*;
import static com.rufus.bumblebee.generators.utils.GeneratorsUtils.checkGeneratorParameters;
import static com.rufus.bumblebee.generators.utils.GeneratorsUtils.getGeneratorDescription;
import static com.rufus.bumblebee.services.dto.ContainerStatus.GENERATION_COMPLETED;
import static com.rufus.bumblebee.services.dto.ContainerStatus.PREPARATION_FOR_GENERATION;

@Service
public class InitGeneratorServiceImpl extends BaseInitGeneratorService<GeneratorRequest> {

    private final ContainerRepository containerRepository;
    private final GeneratorProcessor generatorProcessor;

    public InitGeneratorServiceImpl(ContainerRepository containerRepository,
                                    GeneratorProcessor generatorProcessor) {
        this.containerRepository = containerRepository;
        this.generatorProcessor = generatorProcessor;
    }

    @Override
    public Map<String, String> initGenerators(GeneratorRequest request) throws Exception {
        validateGeneratorsRequest(request);

        Container container = containerRepository.getContainerByCuid(request.getCuid());
        if (GENERATION_COMPLETED.equals(container.getStatus()) || PREPARATION_FOR_GENERATION.equals(container.getStatus())) {
            throw new Exception("Container status exception for status: " + container.getStatus());
        }

        List<Pair> generators = new ArrayList<>(request.getGeneratorInfo().size());
        for (GeneratorInformation generatorInformation : request.getGeneratorInfo()) {
            generators.add(new Pair(
                    getGeneratorDescription(generatorInformation.getGeneratorName()),
                    generatorInformation.getValues()
            ));
        }

        container.setStatus(PREPARATION_FOR_GENERATION);
        generatorProcessor.generateTestData(generators, containerRepository.save(container));

        return new HashMap<String, String>() {{
            put(KEY_CONTAINER_NAME, container.getName());
            put(KEY_UID, container.getCuid().toString());
            put(KEY_STATUS, container.getStatus().name());
            put(KEY_GENERATORS_SIZE, String.valueOf(generators.size()));
        }};
    }

    protected void validateGeneratorsRequest(GeneratorRequest request) throws Exception {
        if (request == null) {
            throw new Exception("Request is null");
        }
        if (StringUtils.isEmpty(request.getCuid())) {
            throw new Exception("Parameter cuid is null or empty ");
        }

        for (GeneratorInformation information : request.getGeneratorInfo()) {
            checkGeneratorParameters(
                    information.getValues(),
                    getGeneratorDescription(information.getGeneratorName())
            );
        }
    }
}
