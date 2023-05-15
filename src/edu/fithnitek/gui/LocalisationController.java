/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import static com.google.common.io.Files.map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.swing.MapView;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javax.swing.JFrame;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.fasterxml.jackson.databind.type.MapType;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.InfoWindowOptions;
import java.awt.BorderLayout;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Nadhem
 */
public class LocalisationController {

    private Button b_offre;
    private Button b_bien;
    private Button b_loc;
    @FXML
    private Button b_back;
    
/*    public LocalisationController(String nName, MapReadyHandler setOnMapReadyHandler){
        JFrame frame = new JFrame(nName);
        setOnMapReadyHandler = (MapStatus status) -> {
        if (status == MapStatus.MAP_STATUS_OK) {
            // Get the map object and set some initial options
            map = (Map) getMap();
            MapOptions mapOptions = new MapOptions();
            MapTypeControlOptions controlOptions = MapTypeControlOptions();
            mapOptions.setMapTypeControlOptions(controlOptions);
            
            map.setOptions(mapOptions);
            map.setCenter(new LatLng(41.8316578, -87.6374727));
            map.setZoom(11.0);
            
            Marker mark = new Marker((com.teamdev.jxmaps.Map) map);
            mark.setPosition(map.getCenter());
            
            Circle circle = new Circle((com.teamdev.jxmaps.Map) map);
            
            circle.setCenter(map.getCenter());
            circle.setRadius(400);
            
            CircleOptions co = new CircleOptions();
            co.setFillColor("#FF0000");
            co.setFillOpacity(0.35);
            
            circle.setOptions(co);
        }
    };
        // Add the map to the center of the frame
    frame.add(this, BorderLayout.CENTER);

    // Set the size and visibility of the frame
    frame.setSize(700, 500);
    frame.setVisible(true);
    }*/
    private void offre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreCovoiturage.FXML"));
        Parent root = loader.load();
        Scene scene = b_offre.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    }

    private void bien(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bien.FXML"));
        Parent root = loader.load();
        Scene scene = b_bien.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    }

    private void map(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Localisation.FXML"));
        Parent root = loader.load();
        Scene scene = b_loc.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreCovoiturage.FXML"));
        Parent root = loader.load();
        Scene scene = b_back.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    
    }
    
    
}
