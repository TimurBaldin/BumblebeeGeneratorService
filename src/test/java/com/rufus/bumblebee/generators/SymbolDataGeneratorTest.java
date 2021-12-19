package com.rufus.bumblebee.generators;

import org.junit.Test;

import static org.junit.Assert.*;

public class SymbolDataGeneratorTest {

    @Test
    public void testAmountValues(){
        SymbolDataGenerator generator=new SymbolDataGenerator();
        generator.setLen("5");
        generator.setCount("5");
        generator.setDataMode("STRING");
        generator.setCascade("false");
        generator.setNull("false");
        generator.getTestData();
        assertEquals(generator.getTestData().size(), 5);
    }

    @Test
    public void testLenTestValue(){
        SymbolDataGenerator generator=new SymbolDataGenerator();
        generator.setLen("5");
        generator.setCount("5");
        generator.setDataMode("STRING");
        generator.setCascade("false");
        generator.setNull("false");
        assertEquals(generator.getTestData().get(0).length(), 5);
    }

}