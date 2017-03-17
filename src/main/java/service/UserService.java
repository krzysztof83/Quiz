package service;

import model.ResultQuize;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Professional on 2017-03-16.
 */
public class UserService {
    private User user;
    private EntityManager entityManager;

    public UserService(User user, EntityManager entityManager) {
        this.user = user;
        this.entityManager = entityManager;
    }

    //public or private?
    private UserService() {

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public UserService setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserService setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean verificationLogin() {

        TypedQuery<Long> typedQuery = entityManager
                .createQuery("SELECT count(login) FROM User where login='" + user.getLogin() + "'", Long.class);
        Long number = typedQuery.getSingleResult();
        boolean flag;
        if (number > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean verificationPassword() {
        TypedQuery<String> typedQuery = entityManager
                .createQuery("SELECT password FROM User where login='" + user.getLogin() + "' ", String.class);
        String passwordFromDB = typedQuery.getSingleResult();
        boolean flag;
        if (passwordFromDB.equals(user.getPassword())) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean changePassword() {
        boolean flag = false;
        entityManager.getTransaction().begin();
        Scanner scanner = new Scanner(System.in);
        TypedQuery<User> typedQuery = entityManager
                .createQuery("SELECT e FROM User e where e.login='" + user.getLogin() + "' ", User.class);
        User user = typedQuery.getSingleResult();

        System.out.println("Change user password");
        System.out.println("Please confirm your current password");
        String oldPassword = scanner.nextLine();
        boolean flagConfirmPassword = oldPassword.equals(user.getPassword());
        if (flagConfirmPassword) {
            System.out.println("Please write your new password");
            String newPassword1 = scanner.nextLine();
            System.out.println("Please confirm your new password");
            String newPassword2 = scanner.nextLine();
            boolean flagEqualsNewPassword = newPassword1.equals(newPassword2);
            if (flagEqualsNewPassword) {
                user.setPassword(newPassword1);
                System.out.println("Change password successfully completed");
                flag = true;
            } else {
                System.out.println("Your new password is not equals with confirmed new password");
            }
        } else {
            System.out.println("Your current password is not correct");
        }
        entityManager.getTransaction().commit();
        return flag;
    }

    public ResultQuize getMyBestResult() {
        TypedQuery<ResultQuize> typedQuery = entityManager
                .createQuery("SELECT e.resultQuize FROM User e where login='" + user.getLogin() + "' ", ResultQuize.class);
        ResultQuize resultQuize = typedQuery.getSingleResult();
        return resultQuize;
    }

    public List<User> getAllUserList() {
        TypedQuery<User> query = entityManager.createQuery("SELECT e FROM User e where Id>0", User.class);
        List<User> userList = query.getResultList();
        return userList;
    }

    public void showAllResult() {
        List<User> userList = getAllUserList();
        for (int i = 0; i < userList.size(); i++) {
            String name = userList.get(i).getLogin();
            Integer result;
            ResultQuize resultQuize = userList.get(i).getResultQuize();
            if (resultQuize == null) {
                result = 0;
            } else {
                result = resultQuize.getQuizeResult();
            }
            System.out.println(name + ": " + result);
        }
    }

    public boolean createNewAccount() {
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create new accoun.");
        System.out.println("Please write your login");
        String name = scanner.nextLine();
        boolean checkLogiUnique = checkLogiIsUnique(name);

        while (!checkLogiUnique) {
            System.out.println("Login: " + name + ", is exist in Database, please choise different login.");
            name = scanner.nextLine();
            checkLogiUnique = checkLogiIsUnique(name);
        }
        System.out.println("Please write your password");
        String newPassword1 = scanner.nextLine();
        System.out.println("Please confirm your password");
        String newPassword2 = scanner.nextLine();
        boolean flagEqualsPassword = newPassword1.equals(newPassword2);


        while (!flagEqualsPassword) {
            System.out.println("Your password is not equals with confirmed password");
            System.out.println("Please write your password");
            newPassword1 = scanner.nextLine();
            System.out.println("Please confirm your password");
            newPassword2 = scanner.nextLine();
            flagEqualsPassword = newPassword1.equals(newPassword2);
        }

        User user = new User(name, newPassword1);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        flag = true;
        return flag;
    }

    private boolean checkLogiIsUnique(String name) {
        List<User> userList = getAllUserList();
        List<String> nameList = new ArrayList<>();
        userList.forEach(e -> nameList.add(e.getLogin()));
        boolean isExistInDB = nameList.contains(name);
        return !isExistInDB;
    }
}
