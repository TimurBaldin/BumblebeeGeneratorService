package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.controllers.dto.ContainerDto;
import com.rufus.bumblebee.controllers.requests.ReportType;

public interface ContainerService {

    ContainerDto createContainer(String name, boolean auth, ReportType type) throws Exception;

    String removeContainer(String cuid) throws Exception;

    ContainerDto getContainerByName(String name) throws Exception;

}
