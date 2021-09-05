package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.services.exceptions.AppException;

public interface ContainerService {

    ContainerDto createContainer(String name, boolean auth, ReportType type) throws AppException;

    String removeContainer(String cuid) throws AppException;

    ContainerDto getContainerByName(String name) throws AppException;

}
