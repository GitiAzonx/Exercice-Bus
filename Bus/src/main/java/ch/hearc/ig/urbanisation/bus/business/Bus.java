/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.urbanisation.bus.business;

/**
 *
 * @author silvio.gutierre
 */
public class Bus {
    
    private int id;
    private double lat;
    private double lon;
    private String direction;
    
    public Bus() {
    }
   
    public Bus(int id, float lat, float lon, String direction) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    
       
}
