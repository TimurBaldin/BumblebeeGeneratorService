package Factories;

import Tools.ClientRepository;
import Tools.TestLinesRepository;

public class RepositoryFactory {
    public static ClientRepository getClient(){
        return new ClientRepository();
    }
    public static TestLinesRepository getLinesRepository(){
        return new TestLinesRepository();
    }
}
