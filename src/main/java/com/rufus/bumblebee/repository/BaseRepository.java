package com.rufus.bumblebee.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Class : Базовый класс для CRUD операций
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Repository
@Transactional
public class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    void create(T value) {
        em.merge(value);
    }

    void remove(T value) {
        em.remove(em.merge(value));
    }

}
