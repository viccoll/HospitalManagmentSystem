package Models;

import Configs.DBConst;
import DBHandlers.PersonalAccountDBHandler;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalAccount implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private String login;
    private String password;
    private int id;

    public PersonalAccount(PersonalAccount personalAccount) {
        this.login = personalAccount.login;
        this.password = personalAccount.password;
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
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public boolean authorize() {
        PersonalAccountDBHandler dataHandler = new PersonalAccountDBHandler();
        ResultSet resultSet = dataHandler.getAllRecords();
        if (resultSet == null) return false;
        else {
            try {
                while (resultSet.next()) {
                    if(this.login.equals(resultSet.getString(DBConst.PERSONAL_LOGIN))
                    && this.password.equals(resultSet.getString(DBConst.PERSONAL_PASSWORD)))
                    {
                      this.id = resultSet.getInt(DBConst.PERSONAL_ACCOUNT_ID);
                      return true;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}
