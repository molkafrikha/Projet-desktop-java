/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.User;
import edu.fithnitek.services.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author T480s
 */
public class LoginController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btnsign;
    @FXML
    private Button fbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) throws SQLException {
        UserCRUD pcd = new UserCRUD();
        String loginText = login.getText();
        String pwdText = pwd.getText();
        if (loginText.isEmpty() || pwdText.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Login or Password blank");
            alert.showAndWait();
        } else {
            try {
                if (pcd.login(loginText, pwdText)) {
                    User user = pcd.getLogedUser(loginText);
                    if (user.getEmail().trim().equalsIgnoreCase("admin")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeDesUtilisateurs.fxml"));
                        Parent root = loader.load();
                        ListeDesUtilisateursController dc = loader.getController();
                        login.getScene().setRoot(root);

                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("compte.fxml"));
                        Parent root = loader.load();
                        CompteController dc = loader.getController();
                        System.out.println(user);
                        dc.setResNom(user.getEmail());
                        dc.setResPrenom(user.getPrenom());
                        dc.setResLogin(loginText);
                        dc.setResPassword(pwdText);
                        login.getScene().setRoot(root);
                    }

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Login or Password wrong");
                    alert.showAndWait();

                }

            } catch (SQLException ex) {

                System.out.println("First error" + ex.getMessage());
            } catch (IOException ex) {

                System.out.println("Second error" + ex.getMessage());
            }

        }
    }
    @FXML
        private void sign (ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp.fxml"));
            Parent root = loader.load();
            SignUpController dc = loader.getController();
            login.getScene().setRoot(root);
        } catch (IOException ex) {
       
            System.out.println(ex.getMessage());
     }  
        }

    @FXML
    private void forgetpwd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("password.fxml"));
            Parent root = loader.load();
            PasswordController dc = loader.getController();
            login.getScene().setRoot(root);
        } catch (IOException ex) {
       
            System.out.println(ex.getMessage());
     } 
        
        
    }

}