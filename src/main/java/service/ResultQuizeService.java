package service;

import model.ResultQuize;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by Professional on 2017-03-17.
 */
public class ResultQuizeService {
    private ResultQuize resultQuize;
    private User user;
    private EntityManager entityManager;

    private ResultQuizeService() {
    }

    public ResultQuizeService(ResultQuize resultQuize, User user, EntityManager entityManager) {
        this.resultQuize = resultQuize;
        this.user = user;
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ResultQuizeService setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ResultQuizeService setUser(User user) {
        this.user = user;
        return this;
    }

    public ResultQuize getResultQuize() {
        return resultQuize;
    }

    public ResultQuizeService setResultQuize(ResultQuize resultQuize) {
        this.resultQuize = resultQuize;
        return this;
    }

    public boolean saveToDB(ResultQuize resultQuizeNew) {
        boolean flag = false;
        entityManager.getTransaction().begin();
        TypedQuery<User> typedQuery = entityManager
                .createQuery("SELECT e FROM User e where e.login='" + user.getLogin() + "' ", User.class);
        User user = typedQuery.getSingleResult();
        if (user.getResultQuize() == null || resultQuize.getQuizeResult() > user.getResultQuize().getQuizeResult()) {
            user.setResultQuize(resultQuizeNew);
            flag = true;
        }
        entityManager.getTransaction().commit();
        return flag;
    }

}
