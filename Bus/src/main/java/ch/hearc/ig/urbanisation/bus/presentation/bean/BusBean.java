/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.urbanisation.bus.presentation.bean;

import ch.hearc.ig.urbanisation.bus.business.Bus;
import ch.hearc.ig.urbanisation.bus.dom.reader.DomReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.xml.parsers.ParserConfigurationException;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.xml.sax.SAXException;

/**
 *
 * @author silvio.gutierre
 */
@Named(value = "busBean")
@ViewScoped
public class BusBean implements Serializable{

    private ArrayList<Bus> buses;
    private MapModel simpleModel;
    
    /**
     * Creates a new instance of BusBean
     */
    public BusBean() {
    }
    
    @PostConstruct
    public void init(){
        try {
            buses = DomReader.getBusReader();
        } catch (IOException ex) {
            Logger.getLogger(BusBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BusBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(BusBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        simpleModel = new DefaultMapModel();  
        
        // Ajout du marker du bureau
        Marker bureau = new Marker(new LatLng(41.980262,-87.668999),"Bureau","orange.png","http://maps.google.com/mapfiles/ms/micons/homegardenbusiness.png");
        simpleModel.addOverlay(bureau);               
        
        // Ajout du marker de l'arrêt de bus
        Marker arret = new Marker(new LatLng(41.984982,-87.668999),"Arrêt");
        simpleModel.addOverlay(arret);       
        
        for(Bus bus:buses){
            if(bus.getDirection().equals("Northbound")){
                Double distanceInMiles = (arret.getLatlng().getLat()-bus.getLat())*69;
                if(distanceInMiles<0.3 && distanceInMiles>0){
                    simpleModel.addOverlay(new Marker(new LatLng(bus.getLat(),bus.getLon()),Integer.toString(bus.getId()),"orange.png","http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));     
                }else{
                    simpleModel.addOverlay(new Marker(new LatLng(bus.getLat(),bus.getLon()),Integer.toString(bus.getId()),"orange.png","http://maps.google.com/mapfiles/ms/micons/bus.png"));     

                }
                
            }
        }
                
    }    

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }    
    
}
