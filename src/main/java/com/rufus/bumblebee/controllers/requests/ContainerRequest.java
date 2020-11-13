package com.rufus.bumblebee.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class ContainerRequest {

    @NotNull(message = "Имя контейнера должно быть задано")
    @Size(min = 1, max = 100)
    private String name;

}
