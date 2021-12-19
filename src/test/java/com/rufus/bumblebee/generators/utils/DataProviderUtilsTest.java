package com.rufus.bumblebee.generators.utils;

import com.rufus.bumblebee.generators.dto.GeneratorResource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DataProviderUtilsTest {

    @Test
    public void getResourceByNameTest() throws IOException {
        GeneratorResource resource = DataProviderUtils.getResourceByName("countryCodes.csv", "Test");
        Assert.assertTrue(resource.getData().size() > 0);
    }
}