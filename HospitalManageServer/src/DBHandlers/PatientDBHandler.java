package DBHandlers;

import Configs.DBConst;
import Models.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDBHandler  {

    public ResultSet getAllRecords() {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + DBConst.PATIENT_TABLE;
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


    public boolean addRecord(Patient patient){
        String insertAddress = "INSERT INTO " + DBConst.ADDRESS_TABLE + "("
                + DBConst.ADDRESS_ID_STREET + "," + DBConst.ADDRESS_FLAT_NUMBER+ ","
                + DBConst.ADDRESS_HOUSE_NUMBER + "," + DBConst.ADDRESS_CORPUS+
                 ")" + "VALUES(?,?,?,?)";

        PreparedStatement prSt;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(insertAddress);
            prSt.setString(1, String.valueOf(patient.getAddress().getStreet().getId()));
            prSt.setString(2, String.valueOf(patient.getAddress().getFlatNumber()));
            prSt.setString(3, String.valueOf(patient.getAddress().getHouseNumber()));
            prSt.setString(4, String.valueOf(patient.getAddress().getCorpus()));
            prSt.executeUpdate();

            String select = "SELECT * FROM " + DBConst.ADDRESS_TABLE  + " ORDER BY "+ DBConst.ADDRESS_ID + " DESC LIMIT 1";
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
             int idAddress = -1;
            try {
                if(resSet != null) {
                    while (resSet.next()){
                       idAddress = resSet.getInt(DBConst.ADDRESS_ID);
                        System.out.println(idAddress);
                    }
                }

            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String insertPatient= "INSERT INTO " + DBConst.PATIENT_TABLE + "("
                    + DBConst.PATIENT_SURNAME + "," + DBConst.PATIENT_NAME+ ","
                    + DBConst.PATIENT_PATRONYMIC + "," + DBConst.PATIENT_BIRTHDAY+ ","
                    + DBConst.PATIENT_PHONE_NUMBER + "," + DBConst.PATIENT_GENDER
                    + "," + DBConst.PATIENT_ID_ADDRESS
                    + ")" + "VALUES(?,?,?,?,?,?,?)";
            prSt = DBConnection.getDbConnection().prepareStatement(insertPatient);
            prSt.setString(1, patient.getSurname());
            prSt.setString(2, patient.getName());
            prSt.setString(3, patient.getPatronymic());
            prSt.setString(4, patient.getBirthday());
            prSt.setString(5, patient.getPhoneNumber());
            prSt.setString(6, patient.getGender());
            prSt.setString(7, String.valueOf(idAddress));
            prSt.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    public ResultSet findByFullname(Patient patient) {
       ResultSet resSet = null;
        String select = "SELECT * FROM " + DBConst.PATIENT_TABLE + " WHERE "
                + DBConst.PATIENT_SURNAME + " = '" + patient.getSurname()+"' AND "
                + DBConst.PATIENT_NAME + " = '" + patient.getName()+"' AND "
                + DBConst.PATIENT_PATRONYMIC + " = '" + patient.getPatronymic()+"'";
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
    public ResultSet findById(int id) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + DBConst.PATIENT_TABLE + " WHERE "
                + DBConst.PATIENT_ID+ " = " + id;
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
