package com.rufus.bumblebee.controllers.requests.user;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class BaseUserRequest {

    @NotNull(message = "login не передан")
    @Size(min = 1, max = 255)
    private String login;

    @NotNull(message = "token не передан")
    @Size(min = 1, max = 1000)
    private String token;

}
