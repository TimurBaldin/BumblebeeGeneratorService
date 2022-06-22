package com.rufus.bumblebee.generators.providers;

import com.rufus.bumblebee.generators.dto.GeneratorResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class DataProviderImpl implements DataProvider<GeneratorResource> {

    //GC очистит, если нужна будет память, либо объект долго не используется
    private static final Map<String, GeneratorResource> GENERATORS_RESOURCE = new HashMap<>();
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private static final ReferenceQueue<Map<String, List<String>>> queue = new ReferenceQueue<>();

    //TODO изменить
    private static final String GENERATORS_DATA_PATH = "./src/main/resources/generators/data/";


    public GeneratorResource getResourceByName(String fileName) throws IOException {
        //TODO потенциальная проблема, getResourceByName может вернуть null
        GeneratorResource resource = GENERATORS_RESOURCE.get(fileName);

        if (resource == null || resource.get() == null) {
            try {
                readWriteLock.writeLock().lock();
                GeneratorResource generatorResource = new GeneratorResource(prepareData(getRawData(fileName)), queue);
                GENERATORS_RESOURCE.put(fileName, generatorResource);
                return generatorResource;

            } finally {
                readWriteLock.writeLock().unlock();
            }
        } else {
            try {
                readWriteLock.readLock().lock();
                return GENERATORS_RESOURCE.get(fileName);
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }

    private Map<String, List<String>> prepareData(List<String> rawData) {
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

    private List<String> getRawData(String fileName) throws IOException {
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
