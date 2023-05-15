/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.services.Email;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author T480s
 */
public class VerificationController implements Initializable {

    @FXML
    private TextField codeId;
    @FXML
    private Button verifBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void verify(ActionEvent event) throws SQLException, IOException {
        Email email = new Email();
        String codeText = codeId.getText();
        Boolean isVerified =   email.isValidCode(codeText);
        if(isVerified){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                    Parent root = loader.load();
                    LoginController dc = new LoginController();
                    codeId.getScene().setRoot(root);    
      }else {
             JOptionPane.showMessageDialog(null, "Please Enter a valide Code"); 
      }   
    }
    
}
