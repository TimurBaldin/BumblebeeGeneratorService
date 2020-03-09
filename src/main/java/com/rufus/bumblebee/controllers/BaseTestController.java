package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.tests.BoundaryTestRequest;
import com.rufus.bumblebee.controllers.requests.tests.IntBoundaryTestRequest;
import com.rufus.bumblebee.controllers.requests.tests.IntRangeTestRequest;
import com.rufus.bumblebee.controllers.requests.tests.SpecialLinesTestRequest;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.TestSuiteBaseService;
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
public class BaseTestController extends BaseController {

    private TestSuiteBaseService service;

    public BaseTestController(@Autowired TestSuiteBaseService service) {
        this.service = service;
    }

    @RequestMapping(path = "/boundary", method = RequestMethod.POST)
    public @ResponseBody
    BaseResponse createBoundaryTest(@RequestBody BoundaryTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionBoundaryTest(
                    request.getLen(), request.getIncreaseQuantity(),
                    request.getLow(), request.getCap(),
                    request.getNullValue(),
                    request.getContainerId()
            );

        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);

        }
        return response;
    }

    @RequestMapping(path = "/spec_symbol", method = RequestMethod.POST)
    public @ResponseBody
    BaseResponse createSpecialTest(@RequestBody SpecialLinesTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionSpecialLinesTest(
                    request.getSpecialLen(), request.getIncreaseQuantity(),
                    request.getEscSpecial(), request.getSpecial(),
                    request.getContainerId()
            );
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;

    }

    @RequestMapping(path = "/int_boundary", method = RequestMethod.POST)
    public @ResponseBody
    BaseResponse createIntBoundaryTest(@RequestBody IntBoundaryTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionIntBoundary(
                    request.getBoundaryIntEnd(),
                    request.getBoundaryIntStart(),
                    request.getQuantity(),
                    request.getContainerId()
            );
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;
    }

    @RequestMapping(path = "/int_range", method = RequestMethod.POST)
    public @ResponseBody
    BaseResponse createIntRangeTest(@RequestBody IntRangeTestRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            ValidatorUtils.validate(request);
            service.selectionIntRange(
                    request.getMaxIntVal(),
                    request.getMinIntVal(),
                    request.getContainerId()
            );
        } catch (Exception ex) {
            return getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), response);
        }
        return response;
    }


    @RequestMapping(path = "/start_generators", method = RequestMethod.POST)
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

}

