package com.rufus.bumblebee.controllers.responses.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private Long userId;
    private String sessionId;

}
