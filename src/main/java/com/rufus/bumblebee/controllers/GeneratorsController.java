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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/generatorManager")
@Api(value = "Controller for generators", tags = {"Controller for generators"})
public class GeneratorsController {

    private final GeneratorService<GeneratorsRequest> generatorService;

    @Autowired
    public GeneratorsController(GeneratorService<GeneratorsRequest> generatorService) {
        this.generatorService = generatorService;
    }

    @PostMapping(path = "/add", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter addGenerators(@RequestBody GeneratorsRequest request) throws Exception {
        SseEmitter emitter = new SseEmitter();
        generatorService.initGenerators(request, emitter);
        return emitter;
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

