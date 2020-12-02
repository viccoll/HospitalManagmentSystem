package DBHandlers;

import Configs.DBConfigs;
import Configs.DBConst;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDBHandler extends DBConfigs implements SQL {
    private ResultSet resSet;
    private String select;
    PreparedStatement prSt;
    @Override
    public ResultSet getAllRecords() {

        resSet = null;
        select = "SELECT * FROM " + DBConst.EMPLOYEE_TABLE;
        PreparedStatement prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return resSet;
    }

    @Override
    public void deleteRecord(Object object) {

    }

    @Override
    public ResultSet findRecord(Object object) {
        return null;
    }

    @Override
    public void addRecord(Object object) {

    }

    public ResultSet findRecordByID(int id) {
        resSet = null;
        select = "SELECT * FROM " + DBConst.EMPLOYEE_TABLE + " WHERE "
                + DBConst.EMPLOYEE_ID + " = " + id;;
         prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return resSet;
    }
}
