package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.services.dto.HistoryDto;

import javax.persistence.NoResultException;

public interface ContainerService {

    ContainerDto createContainer(String name, boolean auth, ReportType type);

    String removeContainer(String cuid) throws NoResultException;

}
