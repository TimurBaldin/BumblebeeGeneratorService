package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.configurer.BaseGenerator;
import com.rufus.bumblebee.repository.TestDataRepository;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseTestService implements TestService {

    private TestDataRepository repository;

    @Override
    public void saveData(List<BaseGenerator> tests) throws InvalidInputException, TransferException {
        if (tests.size() > 0) {
            for (BaseGenerator test : tests) {
                repository.saveTestData(test.getTestData(), test.getContainerRef());
            }
        } else {
            throw new InvalidInputException("It is necessary to choose checks");
        }

    }

    @Autowired
    public void setRepository(TestDataRepository repository) {
        this.repository = repository;
    }
}
