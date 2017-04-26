/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.urbanisation.bus.dom.reader;


import ch.hearc.ig.urbanisation.bus.business.Bus;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author silvio.gutierre
 */
public class BusBuilder {
    
    private ArrayList<Bus> buses;
    
    public BusBuilder(){        
    }
    
    public void build(Document doc) {
        
        //On crée la classe
        buses = new ArrayList<>();

        //Création d'une liste de noeud d'étudiant
        NodeList busesNode = doc.getElementsByTagName("bus");

        for (int i = 0; i < busesNode.getLength(); i++) {
            Element busE = (Element)busesNode.item(i);
            Bus bus = new Bus();
            
            Element idE = (Element)busE.getElementsByTagName("id").item(0);
            bus.setId(Integer.parseInt(idE.getTextContent()));

            Element latE = (Element)busE.getElementsByTagName("lat").item(0);
            bus.setLat(Double.parseDouble(latE.getTextContent()));
            
            Element lonE = (Element)busE.getElementsByTagName("lon").item(0);
            bus.setLon(Double.parseDouble(lonE.getTextContent()));
            
            Element directionE = (Element)busE.getElementsByTagName("pd").item(0);
            bus.setDirection(directionE.getTextContent());

            buses.add(bus);//On ajoute le bus à la liste
        }
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }   
    
}
