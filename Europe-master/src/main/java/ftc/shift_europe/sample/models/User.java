package ftc.shift_europe.sample.models;

import java.util.Collection;
import java.util.List;


public class User {
    private Integer iD;
    private String login;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public void setId(Integer newId) {
        this.iD = newId;
    }

    public Integer getId() {
        return this.iD;
    }

    public void setLogin(String newLogin) {
        this.login = newLogin;
    }

    public String getLogin() {
        return this.login;
    }
}