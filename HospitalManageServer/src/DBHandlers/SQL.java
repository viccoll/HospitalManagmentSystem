package DBHandlers;

import java.sql.ResultSet;

public interface SQL {
    public abstract ResultSet getAllRecords();
    public abstract void deleteRecord(Object object);
    public abstract ResultSet findRecord(Object object);
    public abstract void addRecord(Object object);
}
