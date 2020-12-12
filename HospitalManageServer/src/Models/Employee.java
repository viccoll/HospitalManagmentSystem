package Models;

import Configs.DBConst;
import DBHandlers.EmployeeDBHandler;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employee implements Serializable {
    public static ArrayList<Employee> employeesList = new ArrayList<Employee>();
    private static final long serialVersionUID = 1113799434508676095L;
    public static Employee mainEmployee = new Employee();
    private String surname; private String name; private String patronymic;
    private String gender;
    private int id;
    private String birthday;
    private int idSpecialty;
    private Address Address;
    private String password; private String login;
    private String office;
    private String workTime;

    public Employee(Employee employee) {
        this.id = employee.id;
        this.surname = employee.surname;
        this.name = employee.name;
        this.patronymic = employee.patronymic;
        this.gender = employee.gender;
        this.birthday = employee.birthday;
        this.idSpecialty = employee.idSpecialty;
        this.Address = employee.Address;
    }

    public Employee() {
    }

    public boolean authorize() {
        EmployeeDBHandler dataHandler = new EmployeeDBHandler();
        ResultSet resultSet = dataHandler.getAllRecords();
        if (resultSet == null) return false;
        else {
            try {
                while (resultSet.next()) {
                    if(this.login.equals(resultSet.getString(DBConst.EMPLOYEE_LOGIN))
                            && this.password.equals(resultSet.getString(DBConst.EMPLOYEE_PASSWORD)))
                    {
                        this.id = resultSet.getInt(DBConst.EMPLOYEE_ID);
                        this.idSpecialty = resultSet.getInt(DBConst.EMPLOYEE_ID_SPECIALTY);
                        this.login = resultSet.getString(DBConst.EMPLOYEE_LOGIN);
                        this.password = resultSet.getString(DBConst.EMPLOYEE_PASSWORD);
                        this.surname = resultSet.getString(DBConst.EMPLOYEE_SURNAME);
                        this.name = resultSet.getString(DBConst.EMPLOYEE_NAME);
                        this.patronymic = resultSet.getString(DBConst.EMPLOYEE_PATRONYMIC);
                        this.birthday = resultSet.getString(DBConst.EMPLOYEE_BIRTHDAY);
                        return true;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    public boolean findEmployee(int id) {
        EmployeeDBHandler dataHandler = new EmployeeDBHandler();
        ResultSet resultSet = dataHandler.findRecordByID(id);
        if (resultSet == null) return false;
        else {
            try {
                while (resultSet.next()) {
                  this.id = resultSet.getInt(DBConst.EMPLOYEE_ID);
                  this.surname = resultSet.getString(DBConst.EMPLOYEE_SURNAME);
                  this.name = resultSet.getString(DBConst.EMPLOYEE_NAME);
                  this.patronymic = resultSet.getString(DBConst.EMPLOYEE_PATRONYMIC);
                  this.birthday = resultSet.getString(DBConst.EMPLOYEE_BIRTHDAY);
                  this.idSpecialty = resultSet.getInt(DBConst.EMPLOYEE_ID_SPECIALTY);
                  this.password = resultSet.getString(DBConst.EMPLOYEE_PASSWORD);
                  this.login = resultSet.getString(DBConst.EMPLOYEE_LOGIN);
                  return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public String getSurname() { return surname; }
    public String getName() { return name; }
    public String getPatronymic() { return patronymic; }
    public String getGender() {
        return gender;
    }
    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getBirthday() {
        return birthday;
    }
    public int getIdSpecialty() {
        return idSpecialty;
    }
    public Models.Address getAddress() {
        return Address;
    }
    public String getOffice() {
        return office;
    }
    public String getWorkTime() {
        return workTime;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }
    public void setAddress(Models.Address address) {
        Address = address;
    }
    public void setOffice(String office) {
        this.office = office;
    }
    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}
