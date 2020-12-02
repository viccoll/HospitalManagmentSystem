package Models;

import java.io.Serializable;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private Address address;
    private String gender;
    private String birthday;

    public Patient() {
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
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setGender(String gender) { this.gender = gender; }
}
