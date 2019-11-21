package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.configurer.enums.ClientStatus;
import com.rufus.bumblebee.tables.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Objects;

/**
 * Class : CRUD операции с пользователем
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public Client createClient(Client client) {
        return em.merge(client);
    }

    public Client disableClient(String login) {
        Client client = em.createNamedQuery("SELECT c FROM Client c WHERE c.login :login ", Client.class)
                .setParameter("login", login)
                .getResultList().get(0);
        if (Objects.nonNull(client)) {
            client.setStatus(ClientStatus.LOCK);
            em.merge(client);
        }
        return client;
    }


}
