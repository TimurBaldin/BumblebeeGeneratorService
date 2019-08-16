package com.rufus.bumblebee.services;

import com.rufus.bumblebee.container.Container;
import com.rufus.bumblebee.container.TestDataContainer;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.factories.TestsFactory;
import com.rufus.bumblebee.generators.Rule;
import com.rufus.bumblebee.repository.TestContainerRepository;
import org.apache.log4j.Logger;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class : Сервис создания тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class BaseTestSuiteService implements BaseTestService {

    private static final Logger logger = Logger.getLogger(BaseTestSuiteService.class);

    private List<Rule> tests = new ArrayList<>();
    private TestDataContainer testDataContainer;
    private TestsFactory testsFactory;
    private List<Container> containers = new ArrayList<>();
    private TestContainerRepository repository;

    @Autowired
    public BaseTestSuiteService(TestContainerRepository repository, TestsFactory testsFactory) {
        this.repository = repository;
        this.testsFactory = testsFactory;
    }

    public void selectionBoundaryTest(Integer len, Integer increaseQuantity, Boolean low, Boolean cap, Boolean nullValue) {
        Rule stringBoundaryValues = testsFactory.getBoundaryValues(len, increaseQuantity, low, cap, nullValue, testDataContainer);
        tests.add(stringBoundaryValues);
    }

    public void selectionSpecialLinesTest(Integer specialLen, Integer increaseQuantity, Boolean escSpecial, Boolean special) {
        Rule stringSpecialValues =
                testsFactory.getSpecialValues(specialLen, increaseQuantity, escSpecial, special, testDataContainer);
        tests.add(stringSpecialValues);
    }

    public void selectionIntBoundary(Long boundaryIntEnd, Long boundaryIntStart, Integer quantity) {
        Rule intBoundaryValues =
                testsFactory.getIntBoundaryTest(boundaryIntEnd, boundaryIntStart, quantity, testDataContainer);
        tests.add(intBoundaryValues);
    }

    public void selectionIntRange(Long maxIntVal, Long minIntVal) {
        Rule fullRange = testsFactory.getIntFullRange(maxIntVal, minIntVal, testDataContainer);
        tests.add(fullRange);
    }

    @Override
    public void startGeneratingData() throws GeneratorExceptionInputOptions, TransferException, InvalidInputException {
        if (tests.size() > 0) {
            for (Rule test : tests) {
                test.construct();
            }
            containers.add(testDataContainer);
            tests.clear();
        } else {
            throw new InvalidInputException("It is necessary to choose checks");
        }
    }

    @Override
    public void saveTests() throws InvalidInputException {
        if (containers.size() > 0) {
            for (Container container : containers) {
                repository.create(container.getValues(), container.getContainerName());
                container.clear();
            }
        } else {
            throw new InvalidInputException("It is necessary to choose checks");
        }
    }

    public TestDataContainer getTestDataContainer() {
        return testDataContainer;
    }

}
