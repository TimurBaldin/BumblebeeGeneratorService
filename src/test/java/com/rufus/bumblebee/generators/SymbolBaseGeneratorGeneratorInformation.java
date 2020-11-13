package com.rufus.bumblebee.generators;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

public class SymbolBaseGeneratorGeneratorInformation {

    private static final int LEN = 10;
    private static final int COUNT = 10;
    private static final long CONTAINER_REF = 1;

    private static SymbolBaseGenerator forLenTest;
    private static SymbolBaseGenerator forExTest;

    @BeforeClass
    public static void preparationData() {
        forLenTest=new
                SymbolBaseGenerator();
        forExTest=new
                SymbolBaseGenerator();
    }


    @Test
    public void testSize(){

    }

    @Test
    public void testTransferException()  {

    }

}