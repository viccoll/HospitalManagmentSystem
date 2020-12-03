package DBHandlers;

import Configs.DBConst;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtyDBHandler extends DBConst {
    public String findRecordByID(int id) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + DBConst.SPECIALTY_TABLE + " WHERE "
                + DBConst.SPECIALTY_ID + " = " + id;
        PreparedStatement prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        if(resSet!=null)
        {
            try {
                while (resSet.next()) {
                    return  resSet.getString(DBConst.SPECIALTY_NAME);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
    public ResultSet getAllStreetRecords(){

        ResultSet resSet = null;

        String select = "SELECT * FROM " + DBConst.SPECIALTY_TABLE;
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
