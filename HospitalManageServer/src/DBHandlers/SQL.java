package DBHandlers;

import java.sql.ResultSet;

public interface SQL {
    ResultSet getAllRecords();
    void deleteRecord(Object object);
    ResultSet findRecord(Object object);
    void addRecord(Object object);
}
