package com.rufus.bumblebee.services.generators;

import com.rufus.bumblebee.controllers.requests.GeneratorRequest;
import com.rufus.bumblebee.generators.DataGenerator;
import com.rufus.bumblebee.generators.dto.GeneratorInformation;
import com.rufus.bumblebee.generators.dto.parameters.GeneratorDescription;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.interfaces.DataGenerationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.rufus.bumblebee.BumblebeeGeneratorService.*;
import static com.rufus.bumblebee.generators.utils.GeneratorsUtils.getGeneratorDescriptionByName;
import static com.rufus.bumblebee.services.dto.ContainerStatus.*;

@Service
public class InitGeneratorServiceImpl extends BaseInitGeneratorService<GeneratorRequest> {

    private static final Logger log = LoggerFactory.getLogger(InitGeneratorServiceImpl.class);

    private final ContainerRepository containerRepository;
    private final DataGenerationService dataGenerationService;

    public InitGeneratorServiceImpl(ContainerRepository containerRepository,
                                    DataGenerationService dataGenerationService) {
        this.containerRepository = containerRepository;
        this.dataGenerationService = dataGenerationService;
    }

    @Override
    public Map<String, String> initGenerators(GeneratorRequest request) throws Exception {
        validateGeneratorsRequest(request);

        Container container = containerRepository.getContainerByCuid(request.getCuid());
        if (GENERATION_COMPLETED.equals(container.getStatus()) || PREPARATION_FOR_GENERATION.equals(container.getStatus())) {
            throw new Exception("Container status exception for status: " + container.getStatus());
        }

        List<DataGenerator> generators = new ArrayList<>(request.getGeneratorInfo().size());
        for (GeneratorInformation information : request.getGeneratorInfo()) {
            try {
                generators.add(createGenerator(information.getValues(), information.getGeneratorName()));
            } catch (Exception exception) {
                container.setStatus(GENERATION_ERROR);
                log.error("Error in the process of generating generators", exception);
                throw exception;
            }
        }
        container.setStatus(PREPARATION_FOR_GENERATION);
        dataGenerationService.generateTestData(generators, containerRepository.save(container));

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
            GeneratorDescription generatorDescription = getGeneratorDescription(information.getGeneratorName());
            checkGeneratorParameters(information.getValues(), generatorDescription);
        }
    }

    private GeneratorDescription getGeneratorDescription(String generatorName) throws Exception {
        return Arrays.stream(GeneratorDescription.values())
                .filter(g -> g.getName().equalsIgnoreCase(generatorName))
                .findFirst()
                .orElseThrow(() -> new Exception("Invalid value passed generatorName: " + generatorName));
    }

    private void checkGeneratorParameters(List<Map<String, String>> generatorParameters, GeneratorDescription generatorDescription) throws Exception {
        generatorDescription.getGenerator().validate(generatorParameters);
    }
}
