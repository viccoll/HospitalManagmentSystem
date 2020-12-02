package Models;

import java.io.Serializable;

public class PersonalAccount implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private String login;
    private String password;
//    private int id;

    public PersonalAccount(PersonalAccount personalAccount) {
//        this.id=personalAccount.id;
        this.login=personalAccount.login;
        this.password=personalAccount.password;
    }

    public PersonalAccount() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonalAccount{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}
