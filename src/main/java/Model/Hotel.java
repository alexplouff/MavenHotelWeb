/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Alex
 */
public class Hotel {
    
    private int pk;
    private String name;
    private String street_address;
    private String city;
    private String state;
    private String zip;
    
    public Hotel(String name, String address, String city, String state){
        
        setName(name);
        setStreet_address(address);
        setCity(city);
        setState(state);
    }
    
    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }
    
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getStreet_address() {
        return street_address;
    }

    private void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    private void setZip(String zip) {
        this.zip = zip;
    }
    
    public void setPk(int pk){
        this.pk = pk;
    }
    
    public int getPk(){
        return pk;
    }
    
    public String toString(){
        return name + ", " + zip;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.pk;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.street_address);
        hash = 23 * hash + Objects.hashCode(this.city);
        hash = 23 * hash + Objects.hashCode(this.state);
        hash = 23 * hash + Objects.hashCode(this.zip);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hotel other = (Hotel) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.street_address, other.street_address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        return true;
    }
}
