package com.rufus.bumblebee.Main.Rules.DAO;

import com.rufus.bumblebee.Main.Repository.SessionUntil;
import com.rufus.bumblebee.Main.Tables.Client;
import org.hibernate.SessionFactory;
/**
 * Class : CRUD операции с пользователем
 * @warning : Класс  на этапе разработки . Не используется в текущей реализации
 * @version : 0.0.0
 * @author : Baldin Timur
 */
public interface UserRepository {
    SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();
    boolean create(Client client) throws Exception;

    boolean check(String login, String password);

    boolean change(String parametrName, String changeData);
}
