package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.tables.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Class : CRUD операции с пользователем
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */

@Repository
@Transactional
public class ClientRepository {

    @PersistenceContext
    EntityManager em;

    public Client createClient(Client client) {
        return em.merge(client);
    }

    public Client deleteClient(Client client){
        em.remove(em.merge(client));
        return client;
    }


}
