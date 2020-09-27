package com.rufus.bumblebee.controllers.requests.containers;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class TestDataContainerRequest {

    @NotNull(message = "Имя контейнера должно быть задано")
    @Size(min = 1, max = 100)
    private String name;

}
