/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.User;
import edu.fithnitek.services.Email;
import edu.fithnitek.services.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author T480s
 */
public class SignUpController implements Initializable {

    ObservableList<String> roleList = FXCollections.observableArrayList("DEMANDEUR","OFFREUR");
    private int age= 0;
            
    @FXML
    private TextField login;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField pwd;
    @FXML
    private ComboBox role;
    @FXML
    private Button btnsignUp;
    
    @FXML
    private TextField ageId;
    @FXML
    private Button btnlog;
    @FXML
    private DatePicker birthId;


    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        role.setValue("Role");
        role.setItems(roleList);
    
      
       
        // TODO
    }
      
       
        // TODO
    
    @FXML
    private void signUp (ActionEvent event) throws SQLException  {
        UserCRUD pcd = new UserCRUD();
       Email email = new Email();
       String toEmail = login.getText();
       String pwdText = pwd.getText();
       int ageText = Integer.parseInt(ageId.getText());
       if (toEmail.equals("")&& pwdText.equals("")){         
           JOptionPane.showMessageDialog(null, "Login or Password blank");  
       }
       else{
        try {
            
           if(pcd.isUserExist(toEmail)){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("User Already Exists !");
                    alert.showAndWait();

           } else{
            String nomText = nom.getText();
            String prenomText = prenom.getText();
            String roleText =  (String) role.getValue();
            User p = new User(2, nomText,  prenomText, toEmail ,pwdText,roleText, age);
            pcd.ajouterEntitee(p); 
            int codeNumber =  email.generateCodeAndSaveInDataBase();
            email.sendEmail(toEmail, "Verification", "Please Use this code to verify your account :"+ codeNumber);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("verification.fxml"));
            Parent root = loader.load();
            VerificationController dc = loader.getController();
            login.getScene().setRoot(root);
           }
         
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
 

}

   } @FXML
        private void log (ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController dc = loader.getController();
            login.getScene().setRoot(root);
        } catch (IOException ex) {
       
            System.out.println(ex.getMessage());
     }  
        }
     @FXML
    private void calculateAge(ActionEvent event) {
        LocalDate birthdate = birthId.getValue();
        if (birthdate != null) {
            LocalDate now = LocalDate.now();
            Period period = Period.between(birthdate, now);
            age = period.getYears();
            ageId.setText(String.valueOf(age));
        }
    }
    
    
}
