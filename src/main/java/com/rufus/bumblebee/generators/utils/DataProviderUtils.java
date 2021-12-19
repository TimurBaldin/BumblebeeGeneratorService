package com.rufus.bumblebee.generators.utils;

import com.rufus.bumblebee.generators.dto.GeneratorResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class DataProviderUtils {

    private static final Map<String, GeneratorResource> GENERATORS_RESOURCE = new HashMap<>();
    private static final String GENERATORS_DATA_PATH = "./src/main/resources/generators/data/";

    private DataProviderUtils() {
    }

    public static synchronized GeneratorResource getResourceByName(String fileName, String generatorName) throws IOException {
        if (GENERATORS_RESOURCE.containsKey(generatorName)) {
            return GENERATORS_RESOURCE.get(generatorName);
        }

        List<String> rawData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(GENERATORS_DATA_PATH + fileName).toFile()));) {
            String text;
            while ((text = reader.readLine()) != null) {
                rawData.add(text);
            }
        }

        List<String> keys = new ArrayList<>();
        Collections.addAll(keys, rawData.get(0).split(","));

        int index = 0;
        Map<String, List<String>> data = new HashMap<>();
        for (String key : keys) {
            List<String> values = new ArrayList<>();
            for (int rawKey = 1; rawKey <= rawData.size() - 1; rawKey++) {
                values.add(rawData.get(rawKey).split(",")[index]);
            }
            ++index;
            data.put(key, values);
        }

        GeneratorResource generatorResource = new GeneratorResource(data);
        GENERATORS_RESOURCE.put(generatorName, generatorResource);
        return generatorResource;
    }
}
