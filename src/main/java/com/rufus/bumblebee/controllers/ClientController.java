package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.user.AddUserRequest;
import com.rufus.bumblebee.controllers.requests.user.BaseUserRequest;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.controllers.responses.dto.UserDto;
import com.rufus.bumblebee.services.UserService;
import com.rufus.bumblebee.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user_manager")
public class ClientController {

    private UserService service;

    @Autowired
    public ClientController(UserService service) {
        this.service = service;
    }

    @RequestMapping(path = "/add_user", method = RequestMethod.POST)
    public @ResponseBody
    BaseResponse<UserDto> addClient(@RequestBody AddUserRequest request) {
        BaseResponse<UserDto> response = new BaseResponse<>();
        try {
            ValidatorUtils.validate(request);
            UserDto userDto = service.createUser(request);
            response.setResponse(userDto);
            return response;
        } catch (Exception ex) {
            response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setErrorMessage(ex.getMessage());
            return response;
        }
    }

    @RequestMapping(path = "/disable_user", method = RequestMethod.POST)
    public @ResponseBody
    BaseResponse<UserDto> disableClient(@RequestBody BaseUserRequest request) {
        BaseResponse<UserDto> response = new BaseResponse<>();
        try {
            ValidatorUtils.validate(request);
            UserDto userDto = service.disableUser(request);
            response.setResponse(userDto);
            return response;
        } catch (Exception ex) {
            response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setErrorMessage(ex.getMessage());
            return response;
        }
    }


}
