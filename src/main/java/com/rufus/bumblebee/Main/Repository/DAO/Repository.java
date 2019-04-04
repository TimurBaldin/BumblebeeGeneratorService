package com.rufus.bumblebee.Main.Repository.DAO;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public  class Repository {

    private EntityManager em;

    public EntityManager getEm(){
        return em;
    }

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
