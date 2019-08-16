package com.rufus.bumblebee.repository.DAO;

import com.rufus.bumblebee.repository.SessionUntil;
import com.rufus.bumblebee.tables.Client;
import org.hibernate.SessionFactory;

/**
 * Class : CRUD операции с пользователем
 *
 * @author : Baldin Timur
 * @version : 0.0.0
 * @warning : Класс  на этапе разработки . Не используется в текущей реализации
 */
public interface UserRepository {

    SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();

    boolean create(Client client) throws Exception;

    boolean check(String login, String password);

    boolean change(String parametrName, String changeData);

}
