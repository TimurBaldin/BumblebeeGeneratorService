package com.rufus.bumblebee.Main.Repository;

import com.rufus.bumblebee.Main.Rules.DAO.UserRepository;
import com.rufus.bumblebee.Main.Tables.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClientRepository implements UserRepository {

    private final String CHECK_USER = "SELECT login FROM com.rufus.bumblebee.Main.Tables.Client where login=:val1";
    private Session session;
    private SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();

    @Override
    public boolean create(Client client) throws Exception {
        if (validation(client.getEmail(), client.getLogin(), client.getPassword(), client.getName())) {
            throw new Exception("Login , Email , Password , Name not be null");
        }
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean check(String login, String password) {
        return false;
    }

    private boolean checkDublication(String login) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(CHECK_USER);
        query.setParameter("val1", login);
        String bufer = query.toString();
        if (bufer == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validation(String... data) {
        for (String bufer : data) {
            if (bufer == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean change(String parametrName, String changeData) {
        return false;
    }
}
