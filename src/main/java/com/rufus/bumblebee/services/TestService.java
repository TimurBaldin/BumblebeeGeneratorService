package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.util.List;

public interface TestService {

    void startGeneratingData(List<BaseGenerator> tests) throws GeneratorExceptionInputOptions, TransferException, InvalidInputException;

}
