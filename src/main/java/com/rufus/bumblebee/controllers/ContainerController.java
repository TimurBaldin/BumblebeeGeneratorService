package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.services.ContainerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class ContainerController extends BaseController {

    private final ContainerService service;

    @Autowired
    public ContainerController(ContainerService service) {
        this.service = service;
    }

    @PostMapping(path = "/add/{name}")
    public BaseResponse<ContainerDto> addContainer(@PathVariable("name") @NotBlank @Size(max = 100) String name) {
        BaseResponse<ContainerDto> response = new BaseResponse<>();
        try {
            response.setResponse(service.createTestDataContainer(name));
            return response;
        } catch (Exception ex) {
            return sendErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
    }

    @DeleteMapping(path = "/remove/{id}")
    public BaseResponse removeContainer(@PathVariable("id") @Min(0) Long id) {
        BaseResponse response = new BaseResponse<>();
        try {
            service.removeContainer(id);
            return response;
        } catch (Exception ex) {
            return sendErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
    }
}
