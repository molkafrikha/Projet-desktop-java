/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nadhem
 */
public class SendSmsController {
       // Replace with your Twilio Account SID and Auth Token
    public static final String ACCOUNT_SID = "ACdb7c4d594c9744a4b025ee3abd58fc19";
    public static final String AUTH_TOKEN = "c1fc56312b92ece2deb4c16f10b98b43";
    public static final String TWILIO_NUMBER = "+12766002191";

    @FXML
    private TextField textfield;
    @FXML
    private Label statusLabel;
    private Button b_offre;
    private Button b_bien;
    private Button b_loc;
    @FXML
    private Button b_back;

   
    @FXML
    private void sendSms(ActionEvent event) {
    String toPhoneNumber = textfield.getText();
    if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
        statusLabel.setText("Please enter a phone number.");
        return;
    }

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String messageText = "Bonjour, On va partir dans 30 min préparez-vous " + formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();

    if (message.getSid() != null) {
        statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
    } else {
        statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
    }
}

    @FXML
    private void back(ActionEvent event) throws IOException {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreCovoiturage.FXML"));
        Parent root = loader.load();
        Scene scene = b_back.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    
    }

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
}
    

