package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.TestData;
import javassist.NotFoundException;
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

    public Container createOrUpdateContainer(Container container) {
        return em.merge(container);
    }

    public void removeContainer(Container container){
        em.remove(container);
    }

    public Container getContainerById(Long id) throws NotFoundException {
        Container container = em.find(Container.class, id);
        if (container == null) {
            throw new NotFoundException("Контейнер не найден по ID : " + id);
        }
        return container;
    }

    public List<TestData> getTestDataByContainerId(Long id) throws NotFoundException {
        return getContainerById(id).getData();
    }
}
