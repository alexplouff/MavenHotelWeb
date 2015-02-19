/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface I_HotelDAO {
    
    public abstract void closeDatabaseConnection() throws SQLException;

    public abstract void createRecord(String hotelName, String street, String city, String state) throws ClassNotFoundException, SQLException;

    public abstract void deleteRecord(String primaryKey) throws SQLException, ClassNotFoundException;

    public abstract List<Hotel> getAllRecords() throws Exception;

    public abstract I_DBAccessor getDb();

    public abstract void openDatabaseConnection() throws ClassNotFoundException, SQLException;

    public abstract Hotel retrieveRecordById(String column, int primaryKey) throws ClassNotFoundException, SQLException;

    public abstract void updateRecord(String column, String value, String identifier) throws ClassNotFoundException, SQLException;
    
}
