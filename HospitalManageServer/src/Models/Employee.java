package Models;

import Configs.DBConst;
import DBHandlers.EmployeeDBHandler;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private String surname;
    private String name;
    private String patronymic;
    private String gender;
    private int id;
    private String birthday;
    private String passportSeries;
    private int passportNumber;
    private int idSpecialty;
    private Address Address;

    public Employee(Employee employee) {
        this.id = employee.id;
        this.surname = employee.surname;
        this.name = employee.name;
        this.patronymic = employee.patronymic;
        this.gender = employee.gender;
        this.birthday = employee.birthday;
        this.passportSeries = employee.passportSeries;
        this.passportNumber = employee.passportNumber;
        this.idSpecialty = employee.idSpecialty;
        this.Address = employee.Address;
    }

    public Employee() {

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
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
                  return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}
