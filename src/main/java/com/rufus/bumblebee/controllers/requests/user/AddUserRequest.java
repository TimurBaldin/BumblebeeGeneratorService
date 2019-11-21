package com.rufus.bumblebee.controllers.requests.user;

import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class AddUserRequest extends BaseUserRequest {

    @NotNull(message = "firstName не передан")
    @Size(min = 1, max = 255)
    private String firstName;

    @NotNull(message = "lastName не передан")
    @Size(min = 1, max = 255)
    private String lastName;

    @NotNull(message = "secondName не передан")
    @Size(min = 1, max = 255)
    private String secondName;

    @NotNull(message = "email не передан")
    @Size(min = 1, max = 255)
    private String email;

    @NotNull(message = "роль не передана")
    @Size(min = 1, max = 255)
    private String role;

}
