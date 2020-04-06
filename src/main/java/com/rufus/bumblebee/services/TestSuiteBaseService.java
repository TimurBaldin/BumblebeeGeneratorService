package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.SymbolGenerator;
import com.rufus.bumblebee.generators.configurer.BaseGenerator;
import com.rufus.bumblebee.generators.configurer.DataMode;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.sun.media.sound.InvalidDataException;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class : Сервис создания тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class TestSuiteBaseService extends BaseTestService {

    private List<BaseGenerator> generators = new LinkedList<>();

    public void addSymbolGenerator(int len,
                                   int count,
                                   boolean isNull,
                                   boolean isCascade,
                                   long containerId,
                                   DataMode mode
    ) throws GeneratorExceptionInputOptions, TransferException {
        SymbolGenerator generator = new SymbolGenerator(
                len, count, mode, isNull, isCascade, containerId
        );
        generator.construct();
        generators.add(generator);

    }

    public void startGeneratingData() throws TransferException, InvalidInputException {
        saveData(generators);
    }

}
