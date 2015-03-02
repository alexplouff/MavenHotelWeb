/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class HotelService {

    private I_DBAccessor accessor;
    private I_HotelDAO dao;
    private static final String SELECT_ALL_QUERY = "SELECT * FROM hotel";
    
    public HotelService(I_HotelDAO dao) {
        setDao(dao);
    }

    public I_DBAccessor getAccessor() {
        return accessor;
    }

    public void setAccessor(I_DBAccessor accessor) {
        this.accessor = accessor;
    }

    public I_HotelDAO getDao() {
        return dao;
    }

    public void setDao(I_HotelDAO dao) {
        this.dao = dao;
    }

    public List findAllHotels() throws Exception{
        List list = null;
        try {
            list = dao.getRecords(SELECT_ALL_QUERY);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception se){
            se.getLocalizedMessage();
        }
        return list;
    }
    
    public void createRecord(String hotelName, String street, String city, String state){
        
        try {
            
            dao.createRecord(hotelName, street, city, state);
            
        } catch (ClassNotFoundException ex) {
            ex.getLocalizedMessage();
        } catch (SQLException ex) {
            ex.getLocalizedMessage();
        }
        
    }
    
    public void editRecord(String column, String value, String primaryKey){
        
        try{
            dao.updateRecord(column, value, primaryKey);
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        }
    }
    
    public void deleteRecord(String primaryKey){
        try{
            dao.deleteRecord(primaryKey);
        } catch(Exception ex){
            ex.getLocalizedMessage();
        }
    }
    
    public List getHotelsByName(String name){
       List listOfHotelsByName = null;
        try{
           listOfHotelsByName = dao.getHotelsByName(name);
        } catch (Exception e){
            e.getLocalizedMessage();
        }
        return listOfHotelsByName;
    }
    
    public List getHotelsByStreet(String street){
        List listOfHotelsByStreet = null;
        
       try{
           listOfHotelsByStreet = dao.getHotelsByStreet(street);
       }catch (Exception e) {
           e.getLocalizedMessage();
       }
       return listOfHotelsByStreet;
    }
    
    public List getHotelsByCity(String city){
        List listOfHotelsByCity = null;
        try{
            listOfHotelsByCity = dao.getHotelsByCity(city);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return listOfHotelsByCity;
    }
    
    public List getHotelsByStatew(String state){
        List listOfHotelsByState = null;
        try{
            listOfHotelsByState = dao.getHotelsByState(state);
        } catch(Exception e){
            e.getLocalizedMessage();
        }
        return listOfHotelsByState;
    }

}
