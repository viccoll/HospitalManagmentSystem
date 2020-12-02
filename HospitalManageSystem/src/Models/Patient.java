package Models;

import java.io.Serializable;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    private OutPatientCard outPatientCard;
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private Address address;
    private String passportSeries;
    private int passportNumber;
    private String birthday;

    public Patient(String surname, String name, String patronymic,
                   String phoneNumber, Address address, String passportSeries,
                   int passportNumber, String birthday) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.birthday = birthday;
        this.outPatientCard = new OutPatientCard(10);
    }

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

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    private class OutPatientCard{
      private int id;
      private int department;

        private OutPatientCard(int department) {
            this.department = department;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDepartment() {
            return department;
        }

        public void setDepartment(int department) {
            this.department = department;
        }
    }
}
