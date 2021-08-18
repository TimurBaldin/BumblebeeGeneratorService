package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.repository.tables.Container;

import java.util.List;

public interface AsyncGeneratorService {

    void asyncGenerateTestData(List<BaseGenerator> generators, Container container) throws Exception;

}
