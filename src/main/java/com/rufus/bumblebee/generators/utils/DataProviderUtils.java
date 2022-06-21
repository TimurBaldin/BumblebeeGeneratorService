package com.rufus.bumblebee.generators.utils;

import com.rufus.bumblebee.generators.dto.GeneratorResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataProviderUtils {

    //GC очистит, если нужна будет память
    private static final Map<String, GeneratorResource> GENERATORS_RESOURCE = new WeakHashMap<>();
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private static final String GENERATORS_DATA_PATH = "./src/main/resources/generators/data/";

    private DataProviderUtils() {
    }

    public static GeneratorResource getResourceByName(String fileName, String generatorName) throws IOException {

        if (!GENERATORS_RESOURCE.containsKey(generatorName)) {
            try {
                readWriteLock.writeLock().lock();
                GeneratorResource generatorResource = new GeneratorResource(prepareData(getRawData(fileName)));
                GENERATORS_RESOURCE.put(generatorName, generatorResource);
                return generatorResource;

            } finally {
                readWriteLock.writeLock().unlock();
            }
        } else {
            try {
                readWriteLock.readLock().lock();
                return GENERATORS_RESOURCE.get(generatorName);

            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }

    private static Map<String, List<String>> prepareData(List<String> rawData) {
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
        return data;
    }

    private static List<String> getRawData(String fileName) throws IOException {
        List<String> rawData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(GENERATORS_DATA_PATH + fileName).toFile()))) {
            String text;
            while ((text = reader.readLine()) != null) {
                rawData.add(text);
            }
        }
        return rawData;
    }
}
