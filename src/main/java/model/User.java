package model;

import javax.persistence.*;


/**
 * Created by Professional on 2017-03-16.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private long Id;
    private String login;
    private String password;

    @Embedded
    private ResultQuize resultQuize;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public ResultQuize getResultQuize() {
        return resultQuize;
    }

    public User setResultQuize(ResultQuize resultQuize) {
        this.resultQuize = resultQuize;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
