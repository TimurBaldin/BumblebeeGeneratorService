package com.rufus.bumblebee.Main.Services;

import com.rufus.bumblebee.Main.Container.Container;
import com.rufus.bumblebee.Main.Container.TestDataContainer;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
import com.rufus.bumblebee.Main.Factories.TestsFactory;
import com.rufus.bumblebee.Main.Generators.Rule;
import com.rufus.bumblebee.Main.Repository.TestContainerRepository;
import org.apache.log4j.Logger;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public void createTestDataContainer(String containerName) {
        testDataContainer = testsFactory.getTestDataContainer(containerName);
    }

    public boolean selectionBoundaryTest(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue) {
        try {
            Rule stringBoundaryValues =
                    testsFactory.getBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, testDataContainer);
            tests.add(stringBoundaryValues);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean selectionSpecialLinesTest(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL) {
        try {
            Rule stringSpecialValues =
                    testsFactory.getSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, testDataContainer);
            tests.add(stringSpecialValues);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean selectionIntBoundary(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY) {
        try {
            Rule intBoundaryValues =
                    testsFactory.getIntBoundaryTest(BoundaryIntEnd, BoundaryIntStart, QUANTITY, testDataContainer);
            tests.add(intBoundaryValues);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean selectionIntRange(Long MaxIntVal, Long MinIntVal) {
        try {
            Rule fullRange = testsFactory.getIntFullRange(MaxIntVal, MinIntVal, testDataContainer);
            tests.add(fullRange);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean startGeneratingData() {
        boolean status = false;
        try {
            if (tests.size() > 0) {
                for (Rule test : tests) {
                    test.construct();
                }
                containers.add(testDataContainer);
                tests.clear();
            } else {
                throw new InvalidInputException("It is necessary to choose checks");
            }
        } catch (GeneratorExceptionInputOptions ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getParameters()));
            return status;
        } catch (TransferException e) {
            System.out.println(e.getMessage());
            return status;
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return status;
        }
        status = true;
        return status;

    }

    @Override
    public boolean saveTests() {
        boolean status = false;
        try {
            if (containers.size() > 0) {
                for (Container container : containers) {
                    repository.create(container.getValues(), container.getContainerName());
                    container.clear();
                }
            } else {
                throw new InvalidInputException("It is necessary to choose checks");
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return status;
        }
        status = true;
        return status;
    }

    public TestDataContainer getTestDataContainer() {
        return testDataContainer;
    }

}
