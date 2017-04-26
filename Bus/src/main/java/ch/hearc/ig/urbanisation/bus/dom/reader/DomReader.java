/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.urbanisation.bus.dom.reader;

import ch.hearc.ig.urbanisation.bus.business.Bus;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author silvio.gutierre
 */
public class DomReader {

    public static ArrayList<Bus> getBusReader() throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
            // Rechercher le fichier xml sous forme de chaine de caractère
            URL obj = new URL("http://ctabustracker.com/bustime/map/getBusesForRoute.jsp?route=22");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Créer le document dom depuis la chaine de caractère
            // Création d'une fabrique de documents
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Créaction d'un constructeur de documents
            DocumentBuilder parser = factory.newDocumentBuilder();

            // Conversion de la chaine de caractère en buffer
            StringBuffer Buffer = new StringBuffer(response.toString());
            ByteArrayInputStream bis1 = new ByteArrayInputStream(Buffer.toString().getBytes("UTF-8"));

            // Création du document
            Document document = parser.parse(bis1);

            BusBuilder builder = new BusBuilder();

            builder.build(document);            

            return builder.getBuses();
    }

}
