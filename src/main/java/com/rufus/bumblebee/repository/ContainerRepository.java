package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.tables.Container;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
//@Transactional использовать от spring
//TODO Использовать CRUDrepository
public class ContainerRepository {

    @PersistenceContext
    EntityManager em;

    public Container createOrUpdateContainer(Container container) {
        return em.merge(container);
    }

    public void removeContainer(Container container) {
        em.remove(container);
    }

    public Container getContainerById(String cuid) throws NoResultException {
        return em.createQuery("select c from Container as c where c.cuid=:cuid", Container.class)
                .setParameter("cuid", UUID.fromString(cuid))
                .getSingleResult();
    }

}
