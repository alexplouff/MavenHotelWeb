/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public class HotelDAO implements I_HotelDAO{
    private I_DBAccessor db;
    private static final String db_table = "hotel";

    public HotelDAO() {
        db = new DB_Accessor_SQL("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/sakila",
                    "root", "password");
    }

    @Override
    public I_DBAccessor getDb() {
        return db;
    }

    private void setDb(DB_Accessor_SQL db) {
        this.db = db;
    }

    @Override
    public List<Hotel> getAllRecords() throws Exception {
        List listOfHotels = new ArrayList();
        try {
            listOfHotels = db.getAllRecords();
        } catch (Exception e) {
            e.getLocalizedMessage();
        } finally {
            db.closeDatabaseConnection();
        }

        return listOfHotels;
    }

    @Override
    public void createRecord(String hotelName, String street,
            String city, String state) throws ClassNotFoundException, SQLException {

        Hotel hotel = new Hotel(hotelName, street, city, state);

        List<String> columns = new ArrayList<>();
        columns.add("hotel_id");
        columns.add("hotel_name");
        columns.add("street");
        columns.add("city");
        columns.add("state");
        columns.add("notes");

        List<String> values = new ArrayList<>();

        values.add(null);
        values.add(hotel.getName());
        values.add(hotel.getStreet_address());
        values.add(hotel.getCity());
        values.add(hotel.getState());
        values.add(null);

        db.createRecord(columns, values);
        System.out.println("end of method");
    }

    @Override
    public Hotel retrieveRecordById(String column, int primaryKey)
            throws ClassNotFoundException, SQLException {

        String tableName = "hotel";
        Map<String, Object> rawRecord = db.retrieveRecordById(tableName, column, primaryKey);
        Hotel hotel = new Hotel(
                rawRecord.get("hotel_name").toString(),
                rawRecord.get("street").toString(),
                rawRecord.get("city").toString(),
                rawRecord.get("state").toString());

        return hotel;
    }

    @Override
    public void updateRecord(String column, String value, String primaryKey) throws ClassNotFoundException, SQLException {
        String keyColumn ="hotel_id";
        db.updateRecord(db_table, column, value, keyColumn, primaryKey);

    }

    @Override
    public void openDatabaseConnection() throws ClassNotFoundException, SQLException {
        try {
            db.openDatabaseConnection();

        } catch (SQLException se) {
            se.getLocalizedMessage();
        } catch (ClassNotFoundException cnfe) {
            cnfe.getLocalizedMessage();
        }
    }

    @Override
    public void closeDatabaseConnection() throws SQLException {
        try {
            db.closeDatabaseConnection();
        } catch (SQLException se) {
            se.getLocalizedMessage();
        }
    }

    @Override
    public void deleteRecord(String primaryKey) throws SQLException, ClassNotFoundException {
        final String column = "hotel_id";
        try{
        db.deleteRecord(db_table, column, primaryKey);
        } catch (SQLException se){
            se.getLocalizedMessage();
        }
    }
}
