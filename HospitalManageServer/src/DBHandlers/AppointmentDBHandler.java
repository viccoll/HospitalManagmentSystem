package DBHandlers;

import Configs.DBConst;
import Models.Appointment;
import Models.Employee;
import Models.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDBHandler {
    public ResultSet findRecordsByDoctor(Employee employee){

        ResultSet resSet = null;

        String select = "SELECT * FROM " + DBConst.APPOINTMENT_TABLE + " WHERE "
                + DBConst.APPOINTMENT_ID_EMPLOYEE + " = " + employee.getId();
        System.out.println(select);
        PreparedStatement prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return resSet;
    }

    public void updateAppointment(Patient patient, Appointment appointment) {
        String update = "UPDATE " + DBConst.APPOINTMENT_TABLE + " SET "
                + DBConst.APPOINTMENT_STATUS + "=" + 1 + ", "
                + DBConst.APPOINTMENT_ID_PATIENT + "=" + patient.getId()+", "
                + DBConst.APPOINTMENT_APP_TYPE + "=" + appointment.getIdType()
                + " WHERE " + DBConst.APPOINTMENT_ID+ " = " + appointment.getId();
        System.out.println(update);
        PreparedStatement prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet findRecordsByPatient(Patient patient) {

        ResultSet resSet = null;

        String select = "SELECT * FROM " + DBConst.APPOINTMENT_TABLE + " WHERE "
                + DBConst.APPOINTMENT_ID_PATIENT + " = " + patient.getId();
        System.out.println(select);
        PreparedStatement prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return resSet;
    }

    public ResultSet getAllAppointmentType() {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + DBConst.APPOINTMENT_TYPE_TABLE;
        System.out.println(select);
        PreparedStatement prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return resSet;
    }
}

