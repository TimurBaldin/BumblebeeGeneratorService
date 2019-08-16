package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.Requests.BoundaryTestRequest;
import com.rufus.bumblebee.controllers.Requests.IntBoundaryTestRequest;
import com.rufus.bumblebee.controllers.Requests.IntRangeTestRequest;
import com.rufus.bumblebee.controllers.Requests.SpecialLinesTestRequest;
import com.rufus.bumblebee.controllers.Responses.BaseResponse;
import com.rufus.bumblebee.services.BaseTestSuiteService;
import com.rufus.bumblebee.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/base_tests")
public class BaseTestController {

    private BaseTestSuiteService service;

    public BaseTestController(@Autowired BaseTestSuiteService service) {
        this.service = service;
    }

    @RequestMapping(path = "/boundary", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse createBoundaryTest(@RequestBody BoundaryTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionBoundaryTest(
                    request.getLen(), request.getIncreaseQuantity(),
                    request.getLow(), request.getCap(),
                    request.getNullValue()
            );
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);

        }
        return response;
    }

    @RequestMapping(path = "/spec_symbol", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse createSpecialTest(@RequestBody SpecialLinesTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionSpecialLinesTest(
                    request.getSpecialLen(), request.getIncreaseQuantity(),
                    request.getEscSpecial(), request.getSpecial()
            );
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;

    }

    @RequestMapping(path = "/int_boundary", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse createIntBoundaryTest(@RequestBody IntBoundaryTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionIntBoundary(request.getBoundaryIntEnd(), request.getBoundaryIntStart(), request.getQuantity());
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;
    }

    @RequestMapping(path = "/int_range", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse createIntRangeTest(@RequestBody IntRangeTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionIntRange(request.getMaxIntVal(), request.getMinIntVal());
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;
    }


    @RequestMapping(path = "/start_generators", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse startGeneratingData() {
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

    @RequestMapping(path = "/save_model", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse saveModel() {
        BaseResponse response = new BaseResponse();
        try {
            service.saveTests();
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;
    }

    private BaseResponse getErrorResponse(Integer errorCode, String errorMessage, BaseResponse response) {
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

}

