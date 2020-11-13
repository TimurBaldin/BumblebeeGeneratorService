package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.GeneratorService;
import com.sun.media.sound.InvalidDataException;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;

import static com.rufus.bumblebee.configuration.ControllerURL.GENERATOR_MANAGER;
import static com.rufus.bumblebee.utils.GeneratorValidatorUtils.validate;

@RestController
@RequestMapping(path = GENERATOR_MANAGER)
@Api(value = "Controller for generators", tags = {"Controller for generators"})
@AllArgsConstructor
public class GeneratorsController extends BaseController {

    private final GeneratorService service;

    @PostMapping(value = "/addGenerator")
    public BaseResponse addGenerator(@RequestBody GeneratorsRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            validate(request);
            service.addGenerators(request);
            response.setResponse(request.getContainerId());
        } catch (ValidationException
                | NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | InvalidDataException ex) {
            return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), response);
        }
        return response;
    }


    @GetMapping(path = "/getGenerators")
    public BaseResponse getGenerators() {
        BaseResponse response = new BaseResponse();
        response.setResponse(service.getGeneratorsInformation());
        return response;
    }
}

