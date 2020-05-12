package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.configurer.DataMode;
import org.eclipse.core.runtime.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SymbolGeneratorTest {

    private static final int LEN = 10;
    private static final int COUNT = 10;
    private static final long CONTAINER_REF = 1;

    private static SymbolGenerator forLenTest;
    private static SymbolGenerator forExTest;

    @BeforeClass
    public static void preparationData() {
        forLenTest = new SymbolGenerator(
                LEN, COUNT, DataMode.STRING, true, false, CONTAINER_REF
        );
        forExTest = new SymbolGenerator(
                LEN, COUNT, DataMode.STRING, true, false, CONTAINER_REF
        );

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