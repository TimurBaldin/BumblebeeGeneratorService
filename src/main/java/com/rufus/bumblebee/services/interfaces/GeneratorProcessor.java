package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.dto.Pair;

import java.util.List;

/**
 * Class : сервис генерации тестовых данных
 */
public interface GeneratorProcessor {
    void generateTestData(List<Pair> generators, Container container);
}
