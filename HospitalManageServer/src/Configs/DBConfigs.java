package Configs;

import DBHandlers.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBConfigs {
        protected static final String dbHost = "localhost";
        protected static final String dbPort = "3306";
        protected static final String dbUser = "root";
        protected static final String dbPass = "frontdevQRty1";
        protected static final String dbName = "hospitalsystem";
}
