package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.tables.Container;
import com.sun.media.sound.InvalidDataException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ContainerRepository {

    @PersistenceContext
    EntityManager em;

    public Container createContainer(Container container) {
        return em.merge(container);
    }

    public void removeContainer(Long containerId) {
        em.createNamedQuery("DELETE FROM Container WHERE ID:=s").setParameter("s",containerId).executeUpdate();

    }

    public List<Container> getAll() {
        return em.createNamedQuery("SELECT c FROM Container c", Container.class).getResultList();
    }

    public Container getContainerById(Long id) throws InvalidDataException {
        Container container = em.find(Container.class, id);
        if (container == null) {
            throw new InvalidDataException("Контейнер не найден по ID : " + id);
        }
        return container;
    }

    public List<Container> getAllByUser(Long userId) {
        return em.createNamedQuery("SELECT c FROM Container c ", Container.class).getResultList();
    }

}
