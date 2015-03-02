/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public interface I_DBAccessor {
    
    public abstract void closeDatabaseConnection() throws SQLException;

    public abstract void createRecord(List<String> columns, List<String> values) throws ClassNotFoundException, SQLException;

    public abstract void deleteRecord(Object tableName, Object column, Object primaryKey) throws SQLException;

    public abstract void deleteRecords(Object tableName, Object column, List<Object> primaryKey) throws SQLException;
    
    public abstract List getRecords(String query) throws SQLException, ClassNotFoundException;

    public abstract String getDriverName();

    public abstract String getPassword();

    public abstract String getUrl();

    public abstract String getUserName();

    public abstract void openDatabaseConnection() throws IllegalArgumentException, ClassNotFoundException, SQLException;

    public abstract Map<String, Object> retrieveRecordById(String tableName, 
            String column, int primaryKey) throws ClassNotFoundException, SQLException;

    public abstract void updateRecord(Object tableName, Object column, 
            Object value, Object keyColumn, Object identifier) throws ClassNotFoundException, SQLException;
    
}
