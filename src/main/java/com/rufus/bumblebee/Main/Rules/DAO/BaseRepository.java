package com.rufus.bumblebee.Main.Rules.DAO;

import com.rufus.bumblebee.Main.Repository.SessionUntil;
import org.hibernate.SessionFactory;

import java.util.List;
/**
 * Class : Базовый класс для CRUD операций с тестовыми данными
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public interface BaseRepository<T, S> {
    SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();
    /**
     * Запись тестовых данных в БД
     */
    boolean create(List<S> values, String COLUMN_NAME);
    /**
     * @return выгрузку тестовых данных
     * @param columns название колонок с тестовыми данными
     */
    List<T> get(List<T> columns);
    /**
     * @param columns название колонок для удаления
     */
    boolean delete(List<T> columns);
}
