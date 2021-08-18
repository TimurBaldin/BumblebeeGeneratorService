package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.controllers.responses.GeneratorDto;
import com.rufus.bumblebee.controllers.responses.GeneratorParametersDto;
import com.rufus.bumblebee.generators.annotation.AnnotationHandler;
import com.rufus.bumblebee.generators.annotation.GeneratorDescription;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import com.rufus.bumblebee.services.interfaces.GeneratorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.rufus.bumblebee.configuration.ControllerURL.GENERATOR_MANAGER;

@RestController
@RequestMapping(path = GENERATOR_MANAGER)
@Api(value = "Controller for generators", tags = {"Controller for generators"})
public class GeneratorsController {

    private final GeneratorService<GeneratorsRequest, String> generatorService;

    @Autowired
    public GeneratorsController(GeneratorService<GeneratorsRequest, String> generatorService) {
        this.generatorService = generatorService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addGenerators(@RequestBody GeneratorsRequest request) throws Exception {
        return new ResponseEntity<>(
                "The task for generating test data for a container with CUID " + generatorService.addGenerators(request) + " is registered",
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/information")
    public ResponseEntity<List<GeneratorDto>> getGenerators() {
        return new ResponseEntity<>(getGeneratorsInformation(), HttpStatus.OK);
    }

    private List<GeneratorDto> getGeneratorsInformation() {
        List<GeneratorDto> generatorInfo = new ArrayList<>();
        Map<GeneratorDescription, List<GeneratorParameter>> map = AnnotationHandler.getGeneratorBeans();
        for (Map.Entry<GeneratorDescription, List<GeneratorParameter>> entry : map.entrySet()) {
            generatorInfo.add(new GeneratorDto(
                    entry.getKey().generatorName(),
                    entry.getKey().description(),
                    entry.getValue().stream()
                            .map(s -> new GeneratorParametersDto(s.name(), s.description()))
                            .collect(Collectors.toList())));
        }
        return generatorInfo;
    }
}

