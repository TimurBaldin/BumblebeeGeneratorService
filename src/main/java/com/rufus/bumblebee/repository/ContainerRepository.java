package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.tables.Container;
import javassist.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public class ContainerRepository {

    @PersistenceContext
    EntityManager em;

    public Container createOrUpdateContainer(Container container) {
        return em.merge(container);
    }

    public void removeContainer(Container container) {
        em.remove(container);
    }

    public Container getContainerById(String cuid) throws NotFoundException {
        Container container = em.createQuery("select c from Container as c where c.cuid=:cuid", Container.class)
                .setParameter("cuid", UUID.fromString(cuid))
                .getSingleResult();

        if (container == null) {
            throw new NotFoundException("Контейнер не найден по CUID : " + cuid);
        }
        return container;
    }

}
