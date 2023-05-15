/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.Avis;
import edu.fithnitek.entities.Reclamation;
import edu.fithnitek.services.AvisCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Nihel
 */
public class AvisAdminController implements Initializable {

    @FXML
    private TableView<Avis> table;
    @FXML
    private TableColumn<?, ?> tab_id;
    @FXML
    private TableColumn<?, ?> tab_commentaire;
    
    AvisCRUD rc = new AvisCRUD();
    public static ObservableList<Avis> listA = null;
    @FXML
    private AnchorPane id;
    @FXML
    private Button listU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayAvisBack();
    }    
    
    public void displayAvisBack(){
        
        tab_id.setCellValueFactory(new PropertyValueFactory("id"));
        tab_commentaire.setCellValueFactory(new PropertyValueFactory("commentaire"));
        
        
        List l = rc.selectAll();
        
        listA =FXCollections.observableArrayList(l);
        
        table.setItems(listA);
        
    }
     @FXML
        public void listut(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("listeDesUtilisateurs.fxml"));
            
            Parent root=loader.load();
            listU.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
    
}
