package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1113799434508676095L;
    public static ArrayList<Appointment> appointmentsList = new ArrayList<>();
    private int id;
    private int idEmployee;
    private int idPatient;
    private int idType;
    private String date;
    private String time;
    private String epicrisis;
    private boolean status;

    public void setIdEmployee(int idEmployee) { this.idEmployee = idEmployee; }
    public void setIdPatient(int idPatient) { this.idPatient = idPatient; }
    public void setIdType(int idType) { this.idType = idType; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setStatus(boolean status) { this.status = status; }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", idType=" + idType +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
    public int getIdEmployee() {
        return idEmployee;
    }
    public int getIdPatient() {
        return idPatient;
    }
    public int getIdType() {
        return idType;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getEpicrisis() {
        return epicrisis;
    }
    public int getId() {
        return id;
    }

}
