package com.rufus.bumblebee.generators;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SymbolDataGeneratorTest {

    @Test
    public void testAmountValues() throws Exception {
        List<Map<String, String>> parameters = getParameters("25", "25");
        SymbolDataGenerator generatorV2 = new SymbolDataGenerator();
        generatorV2.validate(parameters);
        assertEquals(25, generatorV2.getTestData(parameters).size());
    }

    @Test(expected = Exception.class)
    public void testAmountValues1() throws Exception {
        List<Map<String, String>> parameters = getParameters("2500", "23");
        SymbolDataGenerator generator = new SymbolDataGenerator();
        generator.validate(parameters);
    }

    private List<Map<String, String>> getParameters(String len, String count) {
        List<Map<String, String>> values = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("len", len);
        map.put("count", count);
        map.put("mode", "STRING");
        map.put("isCascade", "true");
        values.add(map);
        return values;
    }

}