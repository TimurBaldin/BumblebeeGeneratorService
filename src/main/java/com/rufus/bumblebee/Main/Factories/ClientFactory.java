package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Tables.Client;

public class ClientFactory {
    public static Client getClient(String login,String email,String name,String surname,String patronymic,String password){
        Client client=new Client();
        client.setLogin(login);
        client.setEmail(email);
        client.setName(name);
        client.setSurname(surname);
        client.setPatronymic(patronymic);
        client.setPassword(password);
        return client;
    }
}
