package com.rufus.bumblebee.generators.utils;

import com.rufus.bumblebee.generators.dto.GeneratorResource;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Tag("ParallelTests")
@Execution(ExecutionMode.CONCURRENT)
public class DataProviderUtilsParallelTest {

    private static final Map<String, GeneratorResource> resources = new HashMap<>();
    private static final String GENERATOR_NAME_ONE = "Test1";
    private static final String GENERATOR_NAME_TWO = "Test2";
    private static final String FILE_NAME = "countryCodes.csv";

    @BeforeAll
    public static void before() throws IOException {
        //Получаем данные последовательно
        resources.put(GENERATOR_NAME_ONE, DataProviderUtils.getResourceByName(FILE_NAME, GENERATOR_NAME_ONE));
        resources.put(GENERATOR_NAME_TWO, DataProviderUtils.getResourceByName(FILE_NAME, GENERATOR_NAME_TWO));
    }

    @RepeatedTest(5)
    @DisplayName("TestParallel for generator name Test1, for thread-1")
    @ResourceLock(value = "resources")
    public void getResourceByNameTestParallel() throws IOException {
        GeneratorResource resource = DataProviderUtils.getResourceByName(FILE_NAME, GENERATOR_NAME_ONE);
        Assertions.assertTrue(resource.getData().size() > 0);

        //Данные в однопоточной среде, не должны отличаться от данных в многопоточной
        Assertions.assertEquals(resources.get(GENERATOR_NAME_ONE).getData(), resource.getData());
    }

    @RepeatedTest(5)
    @DisplayName("TestParallel for generator name Test1, for thread-2")
    @ResourceLock(value = "resources")
    public void getResourceByNameTestParallel1() throws IOException {
        GeneratorResource resource = DataProviderUtils.getResourceByName(FILE_NAME, GENERATOR_NAME_ONE);
        Assertions.assertTrue(resource.getData().size() > 0);

        //Данные в однопоточной среде, не должны отличаться от данных в многопоточной
        Assertions.assertEquals(resources.get(GENERATOR_NAME_ONE).getData(), resource.getData());
    }

    @RepeatedTest(5)
    @DisplayName("TestParallel for generator name Test2, for thread-3")
    @ResourceLock(value = "resources")
    public void getResourceByNameTestParallel2() throws IOException {
        GeneratorResource resource = DataProviderUtils.getResourceByName(FILE_NAME, GENERATOR_NAME_TWO);
        Assertions.assertTrue(resource.getData().size() > 0);

        //Данные в однопоточной среде, не должны отличаться от данных в многопоточной
        Assertions.assertEquals(resources.get(GENERATOR_NAME_TWO).getData(), resource.getData());
    }

    @RepeatedTest(5)
    @DisplayName("TestParallel for generator name Test2, for thread-4")
    @ResourceLock(value = "resources")
    public void getResourceByNameTestParallel3() throws IOException {
        GeneratorResource resource = DataProviderUtils.getResourceByName(FILE_NAME, GENERATOR_NAME_TWO);
        Assertions.assertTrue(resource.getData().size() > 0);

        //Данные в однопоточной среде, не должны отличаться от данных в многопоточной
        Assertions.assertEquals(resources.get(GENERATOR_NAME_TWO).getData(), resource.getData());
    }

}
