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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author T480s
 */
public class PasswordController implements Initializable {

    @FXML
    private TextField idlog;
    @FXML
    private PasswordField idpwd;
    @FXML
    private Button idConf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    @FXML
    private void toUp(ActionEvent event) {
         UserCRUD pcd = new UserCRUD();
         String loginText = idlog.getText();
        String pwdText = idpwd.getText();
        User ss=new User();
        
                pcd.updateEntitee(ss, loginText, pwdText);
                
                        System.out.println("done");
        
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController dc = loader.getController();
            idConf.getScene().setRoot(root);
        } catch (IOException ex) {
       
            System.out.println(ex.getMessage());
     }  }
    }