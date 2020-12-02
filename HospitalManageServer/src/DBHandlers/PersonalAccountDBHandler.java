package DBHandlers;

import Configs.DBConst;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalAccountDBHandler extends DBConst implements SQL {
    @Override
    public ResultSet getAllRecords() {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + DBConst.PERSONAL_ACCOUNT_TABLE;
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
}
