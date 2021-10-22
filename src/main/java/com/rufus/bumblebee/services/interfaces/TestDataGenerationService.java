package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.generators.DataGenerator;
import com.rufus.bumblebee.repository.tables.Container;

import java.util.List;

public interface TestDataGenerationService {

    void generateTestData(List<DataGenerator> generators, Container container);

}
