package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1113799434508676095L;
    public static ObservableList<Employee> employeesList = FXCollections.observableArrayList();
    public static Employee mainEmployee = new Employee();
    private String surname;
    private String name;
    private String patronymic;
    private String gender;
    private int id;
    private String login;
    private String password;
    private String birthday;
    private int idSpecialty;
    private Address Address;
    private String office;
    private String workTime;

    public Employee(Employee employee) {
        this.id = employee.id;
        this.surname = employee.surname;
        this.name = employee.name;
        this.patronymic = employee.patronymic;
        this.birthday = employee.birthday;
        this.idSpecialty = employee.idSpecialty;
    }

    public Employee() {

    }

    public String getSurname() { return surname; }
    public String getName() { return name; }

    public String getOffice() {
        return office;
    }
    public String getWorkTime() {
        return workTime;
    }
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

    @Override
    public String toString() {
        return   surname + "."+ name.charAt(0)+"."+ patronymic.charAt(0);
    }
}
