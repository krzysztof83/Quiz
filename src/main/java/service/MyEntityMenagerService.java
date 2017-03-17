package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Professional on 2017-03-16.
 */
public class MyEntityMenagerService {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public MyEntityMenagerService setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        return this;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public MyEntityMenagerService setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    public void openMyEntity() {
        setEntityManagerFactory(Persistence.createEntityManagerFactory("myDatabase"));
        setEntityManager(entityManagerFactory.createEntityManager());
    }

    public void closeMyEntity() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
