package io.Factories;

import io.Tools.ClientRepository;
import io.Tools.TestLinesRepository;

public class RepositoryFactory {
    public static ClientRepository getClient() {
        return new ClientRepository();
    }

    public static TestLinesRepository getLinesRepository() {
        return new TestLinesRepository();
    }
}
