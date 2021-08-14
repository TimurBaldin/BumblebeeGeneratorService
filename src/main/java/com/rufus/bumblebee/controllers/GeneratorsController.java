package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.controllers.responses.GeneratorDto;
import com.rufus.bumblebee.services.GeneratorService;
import com.rufus.bumblebee.services.exceptions.GeneratorCreatingException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.rufus.bumblebee.configuration.ControllerURL.GENERATOR_MANAGER;

@RestController
@RequestMapping(path = GENERATOR_MANAGER)
@Api(value = "Controller for generators", tags = {"Controller for generators"})
public class GeneratorsController {

    private final GeneratorService service;

    @Autowired
    public GeneratorsController(GeneratorService service) {
        this.service = service;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addGenerators(@RequestBody GeneratorsRequest request) throws NoResultException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, GeneratorCreatingException {
        return new ResponseEntity<>(
                "The task for generating test data for a container with CUID " + service.addGenerators(request) + " is registered",
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/information")
    public ResponseEntity<List<GeneratorDto>> getGenerators() {
        return new ResponseEntity<>(service.getGeneratorsInformation(), HttpStatus.OK);
    }
}

