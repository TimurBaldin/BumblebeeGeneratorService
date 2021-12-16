package com.rufus.bumblebee.generators;

import java.util.List;

public interface DataGenerator {

    List<String> getTestData();

    String getGeneratorName();

}
