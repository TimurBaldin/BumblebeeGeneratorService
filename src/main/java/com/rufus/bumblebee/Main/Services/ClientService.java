package com.rufus.bumblebee.Main.Services;

import com.rufus.bumblebee.Main.Factories.ClientFactory;
import com.rufus.bumblebee.Main.Rules.BaseService;
import com.rufus.bumblebee.Main.Tables.Client;

public class ClientService implements BaseService {
private Client client;
    public Client createUser(String login,String email,String name,String surname,String patronymic,String password){
        return client= ClientFactory.getClient(login,email,name,surname,patronymic,password);
    }
}
