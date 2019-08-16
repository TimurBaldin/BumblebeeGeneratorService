package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.DAO.UserRepository;
import com.rufus.bumblebee.tables.Client;

/**
 * Class : CRUD операции с пользователем
 *
 * @author : Baldin Timur
 * @version : 0.0.0
 * @warning : Класс  на этапе разработки . Не используется в текущей реализации
 */
public class ClientRepository implements UserRepository {

    private final String CHECK_USER = "SELECT login FROM com.rufus.bumblebee.Main.Tables.Client where login=:val1";

    @Override
    public boolean create(Client client) throws Exception {

        return true;
    }

    @Override
    public boolean check(String login, String password) {
        return false;
    }

    private boolean checkDublication(String login) {
        return true;

    }

    private boolean validation(String... data) {
        return false;
    }

    @Override
    public boolean change(String parametrName, String changeData) {
        return false;
    }
}
