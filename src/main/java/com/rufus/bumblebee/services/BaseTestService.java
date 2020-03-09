package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.repository.TestDataRepository;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseTestService implements TestService {

    @Autowired
    private TestDataRepository repository;

    @Override
    public void startSaveData(List<BaseGenerator> tests) throws InvalidInputException, TransferException {
        try {


            if (tests.size() > 0) {
                for (BaseGenerator test : tests) {
                    repository.saveTestData(test.receivingTestData(), test.getContainerRef());
                }

            } else {
                throw new InvalidInputException("It is necessary to choose checks");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
