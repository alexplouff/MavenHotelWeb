/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class DB_Accessor_SQL implements I_DBAccessor{
    
    private String driverName;
    private String url;
    private String userName;
    private String password;
    private Connection conn;

    public DB_Accessor_SQL() {
    }

    public DB_Accessor_SQL(String driverName, String url, String userName, String password) {
        setDriverName(driverName);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }

    @Override
    public String getDriverName() {
        return driverName;
    }

    private void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void openDatabaseConnection()
            throws IllegalArgumentException, ClassNotFoundException, SQLException {
        try{
        Class.forName(driverName);                    // <----------------------------------------- outdataed / best practice??
        conn = DriverManager.getConnection(url, userName, password);
        System.out.println("Connection Is Open");
        } catch(ClassNotFoundException cnfe){
            cnfe.getLocalizedMessage();
        } catch (SQLException se){
            se.getLocalizedMessage();
        }
    }

    @Override
    public void closeDatabaseConnection() throws SQLException {
        conn.close();
        System.out.println("Connection Is Closed");
    }

    @Override
    public List getAllRecords() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM hotel;";
        List<Map<String, Object>> list = null;
        Map map = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData md = null;

        try {
            openDatabaseConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            list = new ArrayList();

            while (rs.next()) {
                map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    try {
                        map.put(md.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {

                    }
                }
                list.add(map);
            }

        } catch (SQLException se) {
            se.getLocalizedMessage();
        } catch(ClassNotFoundException cnfe){
            cnfe.getLocalizedMessage();
        } finally {
            stmt.close();
            closeDatabaseConnection();
        }
        return list;
    }

    @Override
    public void createRecord(List<String> columns, List<String> values) throws ClassNotFoundException, SQLException {

        StringBuilder sb = new StringBuilder("INSERT INTO hotel (");
        try{
            openDatabaseConnection();
        for (String col : columns) {
            sb.append(col).append(",");
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (");
        for (String val : values) {
            sb.append("?,");
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        PreparedStatement pStmt = conn.prepareStatement(sb.toString());

        for (int i = 0; i < values.size(); i++) {
            pStmt.setObject(i + 1, values.get(i));
        }

        pStmt.executeUpdate();
        }
        catch(SQLException se){
            se.getLocalizedMessage();
        } catch(ClassNotFoundException cnfe){
            cnfe.getLocalizedMessage();
        }finally{
        closeDatabaseConnection();
        }
    }

    @Override
    public Map<String,Object> retrieveRecordById(String tableName, 
            String column, int primaryKey) 
            throws ClassNotFoundException, SQLException {
        
        Map<String,Object> record = null;
        PreparedStatement pStmt = null;
        ResultSetMetaData metaData = null;
        ResultSet rs = null;
        String query = "SELECT * FROM " + tableName + " WHERE " + column + "=" + primaryKey;
        
            try{
                openDatabaseConnection();
                pStmt = conn.prepareStatement(query);
      //          pStmt.setInt(1, primaryKey);
           //     pStmt.executeQuery(query);
                rs = pStmt.executeQuery();
                metaData = rs.getMetaData();
                int colCount = metaData.getColumnCount();
                
                if(rs.next()) {
                    record = new HashMap<>();
                    for(int i=1; i < colCount; i++) {
                        record.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                }
                
            } catch(Exception e){
                e.getLocalizedMessage();
            }finally{
                pStmt.close();
                closeDatabaseConnection();
            }
            
        return record;
    }

    @Override
    public void updateRecord(Object tableName, Object column, Object value,
            Object keyColumn, Object primaryKey) throws ClassNotFoundException, SQLException {
        
        PreparedStatement pStmt = null;
        try{
            openDatabaseConnection();
        
        StringBuilder sb = new StringBuilder("UPDATE ").append(tableName);
        sb.append(" SET ").append(column).append(" = ?");
        sb.append(" WHERE ").append(keyColumn).append(" = ?");

        pStmt = conn.prepareStatement(sb.toString());
        pStmt.setObject(1, value);
        pStmt.setObject(2, primaryKey);
        pStmt.executeUpdate();
        
        } catch(SQLException se){
            se.getLocalizedMessage();
        } catch(ClassNotFoundException cnfe){
            cnfe.getLocalizedMessage();
        }
            finally{
            pStmt.close();
            closeDatabaseConnection();
        }
    }

    @Override
    public void deleteRecord(Object tableName, Object column, Object primaryKey)
           throws SQLException {
        PreparedStatement pStmt = null;
        try{
            openDatabaseConnection();
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(tableName);
        sb.append(" WHERE ").append(column);
        sb.append(" = ").append("?");
        pStmt = conn.prepareStatement(sb.toString());
        pStmt.setObject(1, primaryKey);
        pStmt.executeUpdate();
        
        } catch (SQLException se){
            se.getLocalizedMessage();
        } catch(ClassNotFoundException cnfe){
            cnfe.getLocalizedMessage();
        } finally{
            pStmt.close();
            closeDatabaseConnection();
        }
    }
    


    
    public static void main(String[] args) {
        DB_Accessor_SQL db = new DB_Accessor_SQL("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/sakila",
                "root", "password");
        
        try {
            System.out.println(db.getAllRecords());
        } catch (SQLException ex) {
            Logger.getLogger(DB_Accessor_SQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Accessor_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
