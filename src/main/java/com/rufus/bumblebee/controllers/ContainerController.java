package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.services.ContainerService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.rufus.bumblebee.configuration.ControllerURL.CONTAINER_MANAGER;


@RestController
@RequestMapping(path = CONTAINER_MANAGER)
@Api(value = "Controller for containers", tags = {"Controller for containers"})
@Validated
public class ContainerController {

    private final ContainerService service;

    @Autowired
    public ContainerController(ContainerService service) {
        this.service = service;
    }

    @PostMapping(path = "/add/{name}/{auth}")
    public ResponseEntity<ContainerDto> addContainer(
            @PathVariable("name") @NotBlank @Size(max = 100) String name,
            @PathVariable("auth") Boolean auth) {
        return new ResponseEntity<ContainerDto>(service.createTestDataContainer(name, auth), HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove/{id}")
    public ResponseEntity<String> removeContainer(@PathVariable("id") @Min(0) Long id) throws NotFoundException {
        return new ResponseEntity<String>("The container with the ID " + service.removeContainer(id).toString() + " was deleted", HttpStatus.OK);
    }
}
