package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.tests.SymbolGeneratorRequest;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.TestSuiteBaseService;
import com.rufus.bumblebee.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/base_tests")
public class BaseTestController extends BaseController {

    private TestSuiteBaseService service;

    @Autowired
    public BaseTestController(TestSuiteBaseService service) {
        this.service = service;
    }

    @RequestMapping(path = "/add_symbol_generator", method = RequestMethod.POST)
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

    @RequestMapping(path = "/start_generators", method = RequestMethod.POST)
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

}

