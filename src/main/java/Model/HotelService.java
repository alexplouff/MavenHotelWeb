/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
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
            list = dao.getAllRecords();
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
    
    public void deleteRecord(String primaryKey) throws Exception{
        try{
            dao.deleteRecord(primaryKey);
        } catch(Exception ex){
            ex.getLocalizedMessage();
        }
    }
    
    public static void main(String[] args) {
        HotelService h = new HotelService( new HotelDAO());
//        try {
//            h.createRecord("Scottsdate B&B", "Aryshire", "Men. Falls", "WI");
        h.editRecord("hotel_name", "xxx", "2");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
