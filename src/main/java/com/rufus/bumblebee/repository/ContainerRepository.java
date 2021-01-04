package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.tables.Container;
import javassist.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ContainerRepository {

    @PersistenceContext
    EntityManager em;

    public Container createOrUpdateContainer(Container container) {
        return em.merge(container);
    }

    public void removeContainer(Long containerId) throws Exception {
        em.remove(getContainerById(containerId));
        flushAndClear();
    }

    public Container getContainerById(Long id) throws NotFoundException {
        Container container = em.find(Container.class, id);
        if (container == null) {
            throw new NotFoundException("Контейнер не найден по ID : " + id);
        }
        return container;
    }

    void flushAndClear() {
        em.flush();
        em.clear();
    }
}
