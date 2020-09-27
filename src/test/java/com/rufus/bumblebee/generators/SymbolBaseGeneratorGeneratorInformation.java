package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.exeptions.TransferException;
import org.eclipse.core.runtime.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void testSize() throws TransferException {
        forLenTest.construct();
        Assert.isTrue(forLenTest.getTestData().size() == LEN);
    }

    @Test(expected = TransferException.class)
    public void testTransferException() throws TransferException {
        forExTest.getTestData();
    }

}