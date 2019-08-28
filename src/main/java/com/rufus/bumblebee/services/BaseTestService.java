package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.Rule;
import com.rufus.bumblebee.repository.TestDataRepository;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseTestService implements TestService {

    @Autowired
    private TestDataRepository repository;

    @Override
    public void startGeneratingData(List<Rule> tests) throws GeneratorExceptionInputOptions, TransferException, InvalidInputException {
        if (tests.size() > 0) {
            for (Rule test : tests) {
                test.construct();
            }

            for (Rule test : tests) {
                repository.saveTestData(test.receivingTestData(), test.getContainerRef());
            }
        } else {
            throw new InvalidInputException("It is necessary to choose checks");
        }
    }

}
