
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import edu.fithnitek.services.Email;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author T480s
 */
public class CompteController implements Initializable {
   
     @FXML
    private TextField resNom;
    @FXML
    private TextField resPrenom;
    @FXML
    private TextField resLogin;
    @FXML
    private TextField resPassowrd;
    @FXML
    private Button oc;
    @FXML
    private Button transport_btn;
    @FXML
    private Button eventbtn;
    @FXML
    private Button prod_btn;
    @FXML
    private Button cmd_btn;
    @FXML
    private Button forum_btn;
        @FXML

    private Button rec;
    @FXML
    private Button sond;
    @FXML
    private Button dec;
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
public void setResNom(String message) {
        this.resNom.setText(message);
    }

public TextField getResNom() {
        return resNom;
    }
    public TextField getResLogin() {
        return resLogin;
    }

    public TextField getResPassowrd() {
        return resPassowrd;
    }

    public TextField getResPrenom() {
        return resPrenom;
    }
   //// String username = resNom.getText();
   // User currentUser = User // récupère l'utilisateur depuis la base de données en utilisant le nom d'utilisateur
//int userId = currentUser.getId();

    

    public void setResPrenom(String message) {
        this.resPrenom.setText(message);
    }

     void setResLogin(String message) {
        this.resLogin.setText(message);
    }

    void setResPassword(String message) {
        this.resPassowrd.setText(message);
    }
    


    @FXML
    private void toEvent(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("UserEvents.fxml"));
            Parent root = loader.load();
            eventbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void tovoiture(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ListVoiture.fxml"));
            Parent root = loader.load();
            cmd_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void tomaintenance(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainVoiture.fxml"));
            Parent root = loader.load();
            forum_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @FXML
     public void offreo_view(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("OffreCovoiturage.fxml"));
            
            Parent root=loader.load();
           oc.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
     }

     @FXML
        public void reservation(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("UserRes.fxml"));
            
            Parent root=loader.load();
            forum_btn.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
          @FXML
    private void reclam(ActionEvent event) {
         try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Reclamation.fxml"));
            
            Parent root=loader.load();
            rec.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    @FXML
     private void aviss(ActionEvent event) {
         try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Avis.fxml"));
            
            Parent root=loader.load();
            prod_btn.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void sondage(ActionEvent event) {
         try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Reponses.fxml"));
            
            Parent root=loader.load();
            sond.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void idDec(ActionEvent event) {
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
            
            Parent root=loader.load();
            dec.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}

