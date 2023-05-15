/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import edu.fithnitek.entities.Reclamation;
import static edu.fithnitek.gui.ReclamationController.listR;
import edu.fithnitek.services.ReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author Nihel
 */
public class ReclamationBackController implements Initializable {

    @FXML
    private TableColumn<?, ?> tab_id;
    @FXML
    private TableColumn<?, ?> tab_intitule;
    @FXML
    private TableColumn<?, ?> tab_contenu;
    @FXML
    private TableColumn<?, ?> tab_id_usr;
    @FXML
    private TableView<Reclamation> table;
    
    ReclamationCRUD rc = new ReclamationCRUD();
    public static ObservableList<Reclamation> listR = null;
    @FXML
    private Button listU;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayReclamationBack();
    }    
    public void displayReclamationBack(){
        
        tab_id.setCellValueFactory(new PropertyValueFactory("id"));
        tab_intitule.setCellValueFactory(new PropertyValueFactory("intitule"));
        tab_contenu.setCellValueFactory(new PropertyValueFactory("contenu"));
        tab_id_usr.setCellValueFactory(new PropertyValueFactory("id_usr"));
        
        List l = rc.selectAll();
        
        listR =FXCollections.observableArrayList(l);
        
        table.setItems(listR);
        
    }


    private void treat(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader
                            .load(getClass().getResource("/edu/fithnitek/gui/Home.fxml"));
                    Scene scene = new Scene(root, 300, 250);
           
                    Stage stage = new Stage();
                    stage.setTitle("Reclamations ");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ReclamationBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           
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
    

