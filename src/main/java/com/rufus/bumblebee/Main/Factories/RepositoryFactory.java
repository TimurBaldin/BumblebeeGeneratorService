package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Tools.ClientRepository;
import com.rufus.bumblebee.Main.Tools.TestLinesRepository;
import com.rufus.bumblebee.Main.Tools.TestNumberRepository;

public class RepositoryFactory {
    public static ClientRepository getClient(){
        return new ClientRepository();
    }
    public static TestLinesRepository getLinesRepository(){
        return new TestLinesRepository();

    }
    public static TestNumberRepository getNumberRepository(){
        return new TestNumberRepository();
    }
}
