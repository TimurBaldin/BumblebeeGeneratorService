package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.ContainerRequest;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.services.dto.HistoryDto;
import com.rufus.bumblebee.services.exceptions.AppException;
import com.rufus.bumblebee.services.interfaces.ContainerService;
import com.rufus.bumblebee.services.interfaces.HistoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(path = "/add")
    public ResponseEntity<ContainerDto> addContainer(@RequestBody ContainerRequest request) throws AppException {
        return new ResponseEntity<ContainerDto>(
                service.createContainer(request.getName(), request.getHistoryOn(), request.getReportType()), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/remove/{cuid}")
    public ResponseEntity<String> removeContainer(@PathVariable("cuid") @NotEmpty String cuid) throws AppException {
        return new ResponseEntity<String>("The container with the ID " + service.removeContainer(cuid) + " was deleted", HttpStatus.OK);
    }

    @GetMapping(path = "/history/{cuid}")
    public ResponseEntity<HistoryDto> getHistory(@PathVariable("cuid") @NotEmpty String cuid)throws AppException {
        return new ResponseEntity<HistoryDto>(historyService.getHistory(cuid), HttpStatus.OK);
    }

    @GetMapping(path = "/information/{name}")
    public ResponseEntity<ContainerDto> getContainerByName(@PathVariable("name") @NotEmpty String name) throws AppException {
        return new ResponseEntity<ContainerDto>(service.getContainerByName(name), HttpStatus.OK);
    }
}
