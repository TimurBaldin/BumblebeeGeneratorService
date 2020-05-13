package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.tests.SymbolGeneratorRequest;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.TestSuiteBaseService;
import com.rufus.bumblebee.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.rufus.bumblebee.controllers.config.ControllerURL.BASE_TEST_CONTROLLER;

@RestController
@RequestMapping(path = BASE_TEST_CONTROLLER)
public class BaseTestController extends BaseController {

    private final TestSuiteBaseService service;

    @Autowired
    public BaseTestController(TestSuiteBaseService service) {
        this.service = service;
    }

    @PostMapping(
            value = "/add_symbol_generator", consumes = "application/json", produces = "application/json")
    public BaseResponse createBoundaryTest(@RequestBody SymbolGeneratorRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.addSymbolGenerator(
                    request.getLen(),
                    request.getCount(),
                    request.getIsNull(),
                    request.getIsCascade(),
                    request.getContainerId(),
                    request.getMode()
            );
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);

        }
        return response;
    }

    @GetMapping(path = "/start_generators")
    public BaseResponse startGeneratingData() {
        BaseResponse response = new BaseResponse();
        try {
            service.startGeneratingData();
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;
    }

    @Override
    @GetMapping("/test")
    String urlRequestTest() {
        return TEST_RESULT;
    }

}

