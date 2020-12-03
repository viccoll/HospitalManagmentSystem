package DBHandlers;

import Configs.DBConfigs;
import Configs.DBConst;
import Models.Employee;

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
                + DBConst.EMPLOYEE_ID_SPECIALTY + " = " + id;
         prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return resSet;
    }

    public ResultSet findRecordByLogin(String login) {
        resSet = null;
        select = "SELECT * FROM " + DBConst.EMPLOYEE_TABLE + " WHERE "
                + DBConst.EMPLOYEE_LOGIN + " = " + login;
        prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return resSet;
    }

    public void editLoginRecord(Employee employeeLP) {
        String update = "UPDATE " + DBConst.EMPLOYEE_TABLE + " SET "
                + DBConst.EMPLOYEE_LOGIN + "='" + employeeLP.getLogin() + "'"
                + " WHERE " + DBConst.EMPLOYEE_ID  + " = " + employeeLP.getId();
        prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editPasswordRecord(Employee employeeLP) {
        String update = "UPDATE " + DBConst.EMPLOYEE_TABLE + " SET "
                + DBConst.EMPLOYEE_PASSWORD + "='" + employeeLP.getPassword() + "'"
                + " WHERE " + DBConst.EMPLOYEE_ID  + " = " + employeeLP.getId();
        prSt = null;
        try {
            prSt = DBConnection.getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
