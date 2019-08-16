package com.rufus.bumblebee.repository.DAO;

import com.rufus.bumblebee.repository.SessionUntil;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Class : Базовый класс для CRUD операций с тестовыми данными
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface BaseRepository<T, S> {
    SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();

    /**
     * Запись тестовых данных в БД
     */
    boolean create(List<S> values, String COLUMN_NAME);

    /**
     * @param columns название колонок с тестовыми данными
     * @return выгрузку тестовых данных
     */
    List<T> get(List<T> columns);

    /**
     * @param columns название колонок для удаления
     */
    boolean delete(List<T> columns);
}
