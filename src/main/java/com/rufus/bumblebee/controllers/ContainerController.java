package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.ContainerRequest;
import com.rufus.bumblebee.controllers.dto.ContainerDto;
import com.rufus.bumblebee.services.dto.HistoryDto;
import com.rufus.bumblebee.services.interfaces.ContainerService;
import com.rufus.bumblebee.services.interfaces.HistoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;


@RestController
@RequestMapping(path = "/containerManager")
@Api(value = "Controller for containers", tags = {"Controller for containers"})
@Validated
public class ContainerController {

    private final ContainerService service;
    private final HistoryService<HistoryDto, String> historyService;

    @Autowired
    public ContainerController(ContainerService service, HistoryService<HistoryDto, String> historyService) {
        this.service = service;
        this.historyService = historyService;
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerDto> addContainer(@RequestBody ContainerRequest request) throws Exception {
        return new ResponseEntity<>(
                service.createContainer(request.getName(), request.getHistoryOn(), request.getReportType()), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/remove/{cuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeContainer(@PathVariable("cuid") @NotEmpty String cuid) throws Exception {
        return new ResponseEntity<>("The container with the ID " + service.removeContainer(cuid) + " was deleted", HttpStatus.OK);
    }

    @GetMapping(path = "/history/{cuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoryDto> getHistory(@PathVariable("cuid") @NotEmpty String cuid){
        return new ResponseEntity<>(historyService.getHistory(cuid), HttpStatus.OK);
    }

    @GetMapping(path = "/information/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerDto> getContainerByName(@PathVariable("name") @NotEmpty String name) throws Exception {
        return new ResponseEntity<>(service.getContainerByName(name), HttpStatus.OK);
    }
}
