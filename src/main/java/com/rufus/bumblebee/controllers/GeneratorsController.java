package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.services.dto.GeneratorDto;
import com.rufus.bumblebee.controllers.requests.GeneratorRequest;
import com.rufus.bumblebee.services.interfaces.GeneratorInformationService;
import com.rufus.bumblebee.services.interfaces.GeneratorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/generators")
@Api(value = "Controller for generators", tags = {"Controller for generators"})
@Validated
public class GeneratorsController {

    private final GeneratorService<GeneratorRequest> generatorService;
    private final GeneratorInformationService<List<GeneratorDto>> informationService;

    @Autowired
    public GeneratorsController(GeneratorService<GeneratorRequest> generatorService,
                                GeneratorInformationService<List<GeneratorDto>> informationService) {
        this.generatorService = generatorService;
        this.informationService = informationService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addGenerators(@RequestBody GeneratorRequest request) throws Exception {
        return ResponseEntity.ok(generatorService.initGenerators(request));
    }

    @GetMapping(path = "/information")
    public ResponseEntity<List<GeneratorDto>> getGenerators() {
        return ResponseEntity.ok(informationService.getInformation());
    }
}

