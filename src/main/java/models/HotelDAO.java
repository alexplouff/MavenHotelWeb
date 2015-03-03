/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class HotelDAO implements I_HotelDAO{
    private I_DBAccessor db;
    private static final String DB_TABLE = "hotel";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM hotel";
    
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
    public List<Hotel> getRecords(String query) throws Exception {
        List listOfHotels = new ArrayList();
        try {
            listOfHotels = db.getRecords(query);
        } catch (Exception e) {
            e.getLocalizedMessage();
        } finally {
            db.closeDatabaseConnection();
        }

        return listOfHotels;
    }
    
    @Override
    public List getHotelsByName(String name) throws Exception{
        
        if(name == null || name.length() < 2){
            throw new IllegalArgumentException("Name must be entered");
        }
        
        List list = null;
        String hotelNameConstraint = " WHERE hotel_name='"+name+"';";
           list = getRecords(SELECT_ALL_QUERY + hotelNameConstraint);
        return list;
    }
    
    @Override
    public List getHotelsByCity(String city) throws Exception{
        
        if(city == null || city.length() < 2){
            throw new IllegalArgumentException("Name must be entered");
        }
        
        List list = null;
        String hotelByCityQuery = SELECT_ALL_QUERY + " WHERE city='"+city+"';";
           list = getRecords(hotelByCityQuery);
        return list;
    }
    
    @Override
    public List getHotelsByStreet(String street) throws Exception{
        
        if(street == null || street.length() < 2){
            throw new IllegalArgumentException("Name must be entered");
        }
        
        List list = null;
        String hotelStreetConstraint = " WHERE street='"+street+"';";
           list = getRecords(SELECT_ALL_QUERY + hotelStreetConstraint);
        return list;
    }
    
    @Override
    public List getHotelsByState(String state) throws Exception{
        
        if(state == null || state.length() != 2){
            throw new IllegalArgumentException("Name must be entered");
        }
        
        List list = null;
        String hotelStateConstraint = " WHERE state='"+state+"';";
        list = getRecords(SELECT_ALL_QUERY + hotelStateConstraint);
        return list;
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
        db.updateRecord(DB_TABLE, column, value, keyColumn, primaryKey);

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

    private final static String COLUMN = "hotel_id";
    
    @Override
    public void deleteRecord(String primaryKey) throws SQLException, ClassNotFoundException {
        
        try{
        db.deleteRecord(DB_TABLE, COLUMN, primaryKey);
        } catch (SQLException se){
            se.getLocalizedMessage();
        }
    }
    
    @Override
    public void deleteRecords(List<String> primaryKeys) throws SQLException {
        
        try{
            db.deleteRecord(DB_TABLE, COLUMN, primaryKeys);
        } catch (SQLException se) {
            se.getLocalizedMessage();
        }
    }
    public static void main(String[] args) {

        try {
            HotelDAO h = new HotelDAO();
            
            System.out.println(h.getHotelsByCity("Milwaukee"));
        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
