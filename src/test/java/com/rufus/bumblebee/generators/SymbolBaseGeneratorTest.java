package com.rufus.bumblebee.generators;

import org.junit.Test;

import static org.junit.Assert.*;

public class SymbolBaseGeneratorTest {

    @Test
    public void testAmountValues(){
        SymbolBaseGenerator generator=new SymbolBaseGenerator();
        generator.len=5;
        generator.amount =5;
        generator.mode="STRING";
        generator.isCascade=false;
        generator.isNull=false;
        generator.build();
        assertEquals(generator.build().size(), (int) generator.amount);
    }

    @Test
    public void testLenTestValue(){
        SymbolBaseGenerator generator=new SymbolBaseGenerator();
        generator.len=5;
        generator.amount =5;
        generator.mode="STRING";
        generator.isCascade=false;
        generator.isNull=false;
        generator.build();
        assertEquals(generator.build().get(0).getValue().toString().length(), (int) generator.len);
    }

}