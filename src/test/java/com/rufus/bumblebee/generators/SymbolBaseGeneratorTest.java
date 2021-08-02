package com.rufus.bumblebee.generators;

import org.junit.Test;

import static org.junit.Assert.*;

public class SymbolBaseGeneratorTest {

    @Test
    public void testAmountValues(){
        SymbolBaseGenerator generator=new SymbolBaseGenerator();
        generator.len=5;
        generator.count =5;
        generator.mode="STRING";
        generator.isCascade=false;
        generator.isNull=false;
        generator.build();
        assertEquals(generator.build().size(), (int) generator.count);
    }

    @Test
    public void testLenTestValue(){
        SymbolBaseGenerator generator=new SymbolBaseGenerator();
        generator.len=5;
        generator.count =5;
        generator.mode="STRING";
        generator.isCascade=false;
        generator.isNull=false;
        generator.build();
        assertEquals(generator.build().get(0).length(), (int) generator.len);
    }

}