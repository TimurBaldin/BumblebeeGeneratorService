package com.rufus.bumblebee.services.generators;

import com.rufus.bumblebee.generators.dto.parameters.GeneratorDescription;
import com.rufus.bumblebee.services.dto.GeneratorDto;
import com.rufus.bumblebee.services.dto.GeneratorParametersDto;
import com.rufus.bumblebee.services.interfaces.GeneratorInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorInformationServiceImpl implements GeneratorInformationService<List<GeneratorDto>> {

    private final List<GeneratorDto> generatorInfo = new ArrayList<>(GeneratorDescription.values().length);

    @PostConstruct
    private void initGeneratorDto() {
        for (GeneratorDescription description : GeneratorDescription.values()) {
            generatorInfo.add(new GeneratorDto(description.getName(), description.getDescription(), getGeneratorParametersDtoList(description)));
        }
    }

    @Override
    public List<GeneratorDto> getInformation() {
        return generatorInfo;
    }

    private List<GeneratorParametersDto> getGeneratorParametersDtoList(GeneratorDescription description) {
        List<GeneratorParametersDto> parameters = new ArrayList<>();
        description.getGenerator().getParameters().getFields()
                .forEach((key, value) -> parameters.add(new GeneratorParametersDto(key, value.getDescription())));
        return parameters;
    }
}
