package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.container.TestDataContainer;
import com.rufus.bumblebee.controllers.Requests.TestDataContainerRequest;
import com.rufus.bumblebee.controllers.Responses.BaseResponse;
import com.rufus.bumblebee.services.ContainerService;
import com.rufus.bumblebee.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/container_manager")
public class ContainerController {

    private ContainerService service;

    @Autowired
    public ContainerController(ContainerService service) {
        this.service = service;
    }

    @RequestMapping(path = "/set_container", method = RequestMethod.GET)
    public @ResponseBody
    BaseResponse<TestDataContainer> setTestDataContainer(@RequestBody TestDataContainerRequest request) {
        BaseResponse<TestDataContainer> response = new BaseResponse<>();
        try {
            ValidatorUtils.validate(request);
            TestDataContainer container = service.createTestDataContainer(request.getName());
            response.setResponse(container);
            return response;
        } catch (Exception ex) {
            response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setErrorMessage(ex.getMessage());
            return response;
        }

    }

}
