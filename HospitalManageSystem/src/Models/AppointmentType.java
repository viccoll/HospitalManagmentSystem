package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class AppointmentType implements Serializable {
    public static ObservableList<AppointmentType> appointmentTypeList= FXCollections.observableArrayList();
    private static final long serialVersionUID = 1113799434508676095L;
    private int id;
    private String type;

    @Override
    public String toString() {
        return  type ;
    }

    public int getId() {
        return id;
    }
}
