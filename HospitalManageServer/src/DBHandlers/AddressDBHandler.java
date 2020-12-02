package DBHandlers;

import Configs.DBConst;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDBHandler {
    public ResultSet getAllStreetRecords(){

        ResultSet resSet = null;

        String select = "SELECT * FROM " + DBConst.STREET_TABLE;
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
