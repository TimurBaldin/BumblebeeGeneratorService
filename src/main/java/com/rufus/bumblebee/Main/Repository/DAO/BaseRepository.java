package com.rufus.bumblebee.Main.Repository.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
/**
 * Class : Базовый класс для CRUD операций с тестовыми данными
 * @version : 0.0.1
 * @author : Baldin Timur
 */
@Repository
public interface BaseRepository<T, S> {

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
