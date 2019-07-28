package com.rufus.bumblebee.Main.Services;

public interface BaseTestService {

    void createTestDataContainer(String containerName);

    boolean startGeneratingData();

    boolean saveTests();

}
