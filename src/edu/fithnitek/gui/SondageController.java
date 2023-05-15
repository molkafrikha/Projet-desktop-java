/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.Sondage;
import edu.fithnitek.services.SondageCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SAHBI
 */
public class SondageController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane Transports;
    @FXML
    private TextField chercher;
    @FXML
    private TableView<Sondage> table;
    @FXML
    private TableColumn<Sondage, String> colsujet;
    @FXML
    private TableColumn<Sondage, String> colcategorie;
    @FXML
    private Button btnsupprimer;
       String erreur;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnajoutq;
    @FXML
    private TextField Textcategorie;
    @FXML
    private TextField Textsujet;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnpréc;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
     SondageCRUD ss = new SondageCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addListenerForTable();
        ShowTable();

        
    }  
    @FXML
    private void Chercher(ActionEvent event) {
        FilteredList<Sondage> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Sondage> sort = new SortedList<>(filter);

        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (t.getSujet().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sort);
        });

    }
    public static ObservableList<Sondage> getlistASC() throws SQLException {
        SondageCRUD ts = new  SondageCRUD();
        ObservableList<Sondage> list = FXCollections.observableArrayList(ts.listeDesEntites());
        return list;
    }
     private ObservableList<Sondage> getTableList() {
       
         SondageCRUD ts = new  SondageCRUD();
        ObservableList<Sondage> list = FXCollections.observableArrayList(ts.listeDesEntites());
        return list;
    }
    
    public void ShowTable() {
        ObservableList<Sondage> list = getTableList();
        table.setItems(list);
       // colid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
        
       
    }
  
    
    private void Insert() {
        String sujet = Textsujet.getText();
        String cat = Textcategorie.getText();
        Sondage s = new Sondage(sujet,cat);
       if (ss.nbrs(sujet)==0){
            ss.ajouterEntitee(s);
             System.out.println("Sondage ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Sondage");
            tr.setMessage("Ajoutée avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            ShowTable();
            Textsujet.setText("");
            Textcategorie.setText("");
            
       }else {
          TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Sondage");
            tr.setMessage("ERROR: Sondage déja existe  ");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
 
       }
    } 
    
    @FXML
    private void AjouterT(ActionEvent event) {
        erreur="";
        if (!testSujet()|| !testCategorie()) {
             erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
             System.out.println("Sondage non ajoutéé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Sondage");
            tr.setMessage("Ajoutée failed ");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
        }else{ 
            
         Insert();
        }
       
    }
    
    private void addListenerForTable(){
        table.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
            btnModifier.setDisable(false);
            btnsupprimer.setDisable(false);
            Textsujet.setText(newselection.getSujet());
            Textcategorie.setText(newselection.getCategorie());
        }else{
               Textsujet.setText("");
               Textcategorie.setText("");
               btnModifier.setDisable(true);
               btnsupprimer.setDisable(true);
        }
        }); 
    
    }

    @FXML
    private void ModifierT(ActionEvent event) {
        Sondage sondage = table
                .getSelectionModel().getSelectedItem();
        String sujet = Textsujet.getText();
        String cat = Textcategorie.getText();
        Sondage s = new Sondage(sondage.getSondage_id(),sujet,cat);
        
        // BOX CONFIRMATION 
//        Stage stage = (Stage)MyAnchorPane.getScene().getWindow();
        Stage stage = new Stage();
        
        Alert.AlertType type =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez-vous modifier le sondage?");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
            ss.modifier(s);
            ShowTable();
            
        //NOTIFICATION
        
         System.out.println("Sondage Modifiée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Sondage");
            tr.setMessage("Modifiée avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
       
          
            
        
    }

    @FXML
    private void SupprimerT(ActionEvent event) {
         Sondage sondage = table.getSelectionModel().getSelectedItem();
        String sujet = Textsujet.getText();
        String cat = Textcategorie.getText();
        int id = sondage.getSondage_id();
        
         // BOX CONFIRMATION 
//        Stage stage = (Stage)MyAnchorPane.getScene().getWindow();
        Stage stage = new Stage();
        
        Alert.AlertType type =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez-vous supprimer le sondage?");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
            ss.supprimer(id);
            ShowTable();
            
        //NOTIFICATION
        
         System.out.println("Sondage Supprimée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Sondage");
            tr.setMessage("Supprimé avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
       
          
    }

    @FXML
    private void AjouterQuestion(ActionEvent event) {
        
          try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("Questions.fxml"));
            Parent root = loader.load();
           
            QuestionsController questionController = loader.getController();
            questionController.showInformation(Textsujet.getText());
            
            Scene scene = new Scene(root);
            Stage stage =(Stage)btnAjouter.getScene().getWindow();
            stage.setScene(scene);
            //stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }

   //----------------------Control saisie----------------------
    
    @FXML
    private boolean testSujet() {
         int nbNonChar = 0;
        for (int i = 1; i < Textsujet.getText().trim().length(); i++) {
            char ch = Textsujet.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Textsujet.getText().trim().length() >= 3) {
          //  nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
           // nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }

    @FXML
    private boolean testCategorie() {
           int nbNonChar = 0;
        for (int i = 1; i < Textcategorie.getText().trim().length(); i++) {
            char ch = Textcategorie.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Textcategorie.getText().trim().length() >= 3) {
          //  nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
           // nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }


    @FXML
    private void goBack(ActionEvent event) {
         try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/GestHomePage.fxml"));
            Parent root = loader.load();
          
            Scene scene = new Scene(root);
            Stage stage =(Stage)btnpréc.getScene().getWindow();
            stage.setScene(scene);
            //stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }


}

