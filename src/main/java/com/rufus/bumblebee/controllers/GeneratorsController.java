package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.controllers.responses.GeneratorDto;
import com.rufus.bumblebee.services.GeneratorService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.rufus.bumblebee.configuration.ControllerURL.GENERATOR_MANAGER;

@RestController
@RequestMapping(path = GENERATOR_MANAGER)
@Api(value = "Controller for generators", tags = {"Controller for generators"})
@AllArgsConstructor
public class GeneratorsController {

    private final GeneratorService service;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addGenerator(@RequestBody GeneratorsRequest request) throws NotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        service.addGenerators(request);
        return new ResponseEntity<String>(
                "The task for generating test data for a container with an ID " + request.getContainerId() + " is registered",
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/getInformation")
    public ResponseEntity<List<GeneratorDto>> getGenerators() {
        return new ResponseEntity<List<GeneratorDto>>(service.getGeneratorsInformation(), HttpStatus.OK);
    }
}

