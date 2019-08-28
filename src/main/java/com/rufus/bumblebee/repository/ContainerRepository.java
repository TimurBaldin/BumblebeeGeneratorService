package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.tables.Container;
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

    public List<Container> getAll() {
        return em.createNamedQuery("SELECT c FROM Container c", Container.class).getResultList();
    }

    public Container getContainerById(Long id) {
        return em.find(Container.class, id);
    }

    public List<Container> getAllByUser(Long userId) {
        return em.createNamedQuery("SELECT c FROM Container c ", Container.class).getResultList();
    }

}
