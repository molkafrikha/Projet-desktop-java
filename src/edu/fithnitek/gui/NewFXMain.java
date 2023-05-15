/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nadhem
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage){
        try {
            Parent root =FXMLLoader.load(getClass().getResource("Localisation.fxml"));
            Scene OffreCovoiturage = new Scene(root);
            primaryStage.setTitle("Fi Thnitek");
            primaryStage.setScene(OffreCovoiturage);
            primaryStage.show();
        } catch (IOException ex) {
           ex.printStackTrace();        
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
