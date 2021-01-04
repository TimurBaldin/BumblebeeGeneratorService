package com.rufus.bumblebee.controllers.responses;

import com.rufus.bumblebee.repository.ContainerStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContainerDto {

    private Long id;
    private String name;
    private ContainerStatus status;

}
