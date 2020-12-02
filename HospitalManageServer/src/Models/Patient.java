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
    private String birthday;
    private String gender;

    public Patient(Patient patient) {
        this.surname = patient.surname;
        this.name = patient.name;
        this.patronymic = patient.patronymic;
        this.phoneNumber = patient.phoneNumber;
        this.address =patient.address;
        this.birthday = patient.birthday;
        this.gender = patient.gender;
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }

    public String getSurname() { return surname; }
    public String getName() { return name; }
    public String getPatronymic() { return patronymic; }
    public String getPhoneNumber() { return phoneNumber; }
    public Address getAddress() { return address; }
    public String getBirthday() { return birthday; }
    public String getGender() { return gender; }
}
