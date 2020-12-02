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

    public Patient(Patient patient) {
        this.surname = patient.surname;
        this.name = patient.name;
        this.patronymic = patient.patronymic;
        this.phoneNumber = patient.phoneNumber;
        this.address =patient.address;
        this.passportSeries = patient.passportSeries;
        this.passportNumber = patient.passportNumber;
        this.birthday = patient.birthday;
        this.outPatientCard = new OutPatientCard(10);
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


    private class OutPatientCard implements Serializable{
      private int id;
      private int department;

        private OutPatientCard(int department) {
            this.department = department;
        }
    }
}
