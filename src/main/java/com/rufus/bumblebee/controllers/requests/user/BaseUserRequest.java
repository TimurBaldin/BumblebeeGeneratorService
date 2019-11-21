package com.rufus.bumblebee.controllers.requests.user;

import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

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
