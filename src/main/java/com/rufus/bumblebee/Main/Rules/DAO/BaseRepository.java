package com.rufus.bumblebee.Main.Rules.DAO;

import com.rufus.bumblebee.Main.Repository.SessionUntil;
import org.hibernate.SessionFactory;

import java.util.List;

public interface BaseRepository<T, S> {
    SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();
    boolean create(List<S> values, String COLUMN_NAME);

    List<T> get(List<T> columns);

    boolean delete(List<T> columns);
}
