package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

public interface BaseTestService {

    void startGeneratingData() throws GeneratorExceptionInputOptions, TransferException, InvalidInputException;

    void saveTests() throws InvalidInputException;

}
