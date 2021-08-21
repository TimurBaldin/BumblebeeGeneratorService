package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.tables.Container;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public interface CustomContainerRepository {

    Container getContainerByCuid(String cuid) throws NoResultException;

}
