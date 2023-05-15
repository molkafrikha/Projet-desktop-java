/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.OffreCovoiturage;
import edu.fithnitek.entities.demandecovoiturage;
import edu.fithnitek.services.OffreCovoiturageCRUD;
import edu.fithnitek.services.reservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
public class UserResController implements Initializable {
private  Parent fxml;
    @FXML
    private AnchorPane combNP;
    @FXML
    private TextField lieua;
    @FXML
    private TextField tflieud;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btndelete;
    @FXML
    private TableView<OffreCovoiturage> tableuser;
    @FXML
    private TableColumn<OffreCovoiturage, String> colD;
    @FXML
    private TableColumn<OffreCovoiturage, String> colA;
    @FXML
    private TableColumn<OffreCovoiturage, Integer> colnbplace;
    @FXML
    private TableColumn<OffreCovoiturage, String> coldate;
    @FXML
    private Button btnChercher;
    @FXML
    private ComboBox<Integer> combnp;
    @FXML
    private DatePicker date;
    @FXML
    private Button cmpt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowTable();
         ObservableList<Integer> list = FXCollections.observableArrayList(1,2,3,4);
         combnp.setItems(list);
        addListenerForTable();
        ShowTable();
    }

    private ObservableList<OffreCovoiturage> getTableList() throws SQLException {
        OffreCovoiturageCRUD ts = new OffreCovoiturageCRUD();

        ObservableList<OffreCovoiturage> list = FXCollections.observableArrayList(ts.selectAll());

        return list;

    }

    public void ShowTable() {
       
    try {
        ObservableList<OffreCovoiturage> Mylist = getTableList();
        tableuser.setItems(Mylist);
        // colid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
        colD.setCellValueFactory(new PropertyValueFactory<>("lieuD"));
        colA.setCellValueFactory(new PropertyValueFactory<>("lieuA"));
        colnbplace.setCellValueFactory(new PropertyValueFactory<>("ndPlace"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("dateD"));
    } catch (SQLException ex) {
        Logger.getLogger(UserResController.class.getName()).log(Level.SEVERE, null, ex);
    }
        

    }

    @FXML
    private void ajouteRe(ActionEvent event) {
        
    
        OffreCovoiturage offre = tableuser.getSelectionModel().getSelectedItem();
        if ((lieua.getText().isEmpty()) || (tflieud.getText().isEmpty()) || (combnp.getValue()== null) || (date.getValue()== null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention ");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            reservationCRUD pcd = new reservationCRUD();
            int nbplace = combnp.getValue();
            java.sql.Date dateReservation = java.sql.Date.valueOf(date.getValue());
            int idoffre= offre.getId();
            OffreCovoiturage o = pcd.getIdDO(idoffre);
            
            demandecovoiturage d = new demandecovoiturage(dateReservation, nbplace,o);
            
            
          
            
               Stage stage = new Stage();
        
        Alert.AlertType typee =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(typee,"");
        alert.initModality(Modality.APPLICATION_MODAL);
         alert.getDialogPane().setContentText("Voulez-vous ajouter la réservation?");
        alert.getDialogPane().setHeaderText("Confirmation");
           Optional<ButtonType> result = alert.showAndWait();
          
            
             if (result.get()== ButtonType.OK){
              if (nbplace <= 4) {
                pcd.ajouterEntitee(d);
                System.out.println("reservation ajoutééé");
               
            ShowTable();
           
            
        
            System.out.println("réservation ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Réservation");
            tr.setMessage("ajoutééé avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
        }
        
    
        
    }}

    @FXML
    private void updateRe(ActionEvent event) {
        reservationCRUD pcd = new reservationCRUD();
        OffreCovoiturage offre = tableuser.getSelectionModel().getSelectedItem();
        int nbplace = combnp.getValue();
         java.sql.Date dateReservation = java.sql.Date.valueOf(date.getValue());
         System.out.println("111111111111");
         demandecovoiturage objd = pcd.getIdDC(offre.getId());
            
      //  demandecovoiturage d = new demandecovoiturage(objd.getId(),nbplace);
        demandecovoiturage d = new demandecovoiturage(dateReservation, nbplace, offre);
        d.setId(objd.getId());
        System.out.println("dddddddddddddddddddd  " + d);
        
            
            System.out.println("réservation modifiée");
            System.out.println(" d after   " + d);

//            JOptionPane.showMessageDialog(null, "réservation modifiée !");
//
//        } else {
//            JOptionPane.showMessageDialog(null, "vérifiez nombre de place  !");
//
//        }
           Stage stage = new Stage();
        
        Alert.AlertType typee =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(typee,"");
        alert.initModality(Modality.APPLICATION_MODAL);
         alert.getDialogPane().setContentText("Voulez-vous modifier la réservation?");
        alert.getDialogPane().setHeaderText("Confirmation");
           Optional<ButtonType> result = alert.showAndWait();
           if (result.get()== ButtonType.OK){
              
              pcd.modifier(d);
               
            ShowTable();
            
        
            System.out.println("réservation modifiée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Reservation");
            tr.setMessage("Ajout avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
        

    }

    
    
    
    
    
    
    
    
    
    
        private void addListenerForTable(){
             demandecovoiturage d = new demandecovoiturage();
             reservationCRUD pcd = new reservationCRUD();
        tableuser.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
               int id =newselection.getId();
              // System.out.println(id);
               demandecovoiturage obj = pcd.getIdDC(newselection.getId());
               System.out.println(obj);
            int nbp = pcd.getIdDC(newselection.getId()).getNbplace();
            //System.out.println(nbp);
           
           btnValider.setDisable(false);
           btnUpdate.setDisable(false);
            btndelete.setDisable(false);
            tflieud.setText(newselection.getLieuD());
            lieua.setText(newselection.getLieuA());
            combnp.setValue(nbp);
         //  date.setValue(newselection.getDateD());
            
        }else{
               tflieud.setText("");
               lieua.setText("");
               combnp.setValue(null);
              // date.setText("");
               btnValider.setDisable(true);
               btnUpdate.setDisable(true);
               btndelete.setDisable(true);
        }
        }); 
//     private void reservation(ActionEvent event) throws IOException {
//            
//           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/GestionDesReservations.fxml"));
//           Parent root1 = (Parent) fxmlLoader.load();
//           Stage stage = new Stage();
//           stage.setScene(new Scene(root1));
//           stage.show();
//          
// 
//     }
    }
//        
//         
//    }      
//    }
//    
//    private void goBack(ActionEvent event) {
//        
//        
//         try {
//             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/GestHomePage.fxml"));
//            Parent root = loader.load();
//          
//            Scene scene = new Scene(root);
//            Stage stage =(Stage)btnpre.getScene().getWindow();
//            stage.setScene(scene);
//            //stage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());  
//        }
//    }

    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        OffreCovoiturageCRUD ts = new OffreCovoiturageCRUD();
        List<OffreCovoiturage> List = ts.selectAll();
        ObservableList<OffreCovoiturage> newList
                = List.stream()
                        .filter((OffreCovoiturage q) -> (q.getLieuD().equals(tflieud.getText())))
                        .filter((OffreCovoiturage q) -> (q.getLieuA().equals(lieua.getText())))
                        .filter((OffreCovoiturage q) -> (q.getDateD().equals(java.sql.Date.valueOf(date.getValue()))))
                        .filter((OffreCovoiturage q) -> (4 - q.getNbPlace()>= combnp.getValue()))
                        .collect(Collector.of(
                                FXCollections::observableArrayList,
                                ObservableList::add,
                                (l1, l2) -> {
                                    l1.addAll(l2);
                                    return l1;
                                })
                        );
tableuser.setItems(newList);
    }

    @FXML
    private void deleteRe(ActionEvent event) {
        
      reservationCRUD pcd = new reservationCRUD();

      OffreCovoiturage d = tableuser.getSelectionModel().getSelectedItem();
      demandecovoiturage obj= new demandecovoiturage();
      
         demandecovoiturage objd = pcd.getIdDC(d.getId());
        
         // BOX CONFIRMATION 
//        Stage stage = (Stage)MyAnchorPane.getScene().getWindow();
        Stage stage = new Stage();
        
        Alert.AlertType type =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez-vous supprimer la réservation?");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
              pcd.supprimer(objd);

            ShowTable();
            
        //NOTIFICATION
        
         System.out.println("reservation supprimée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Réservation");
            tr.setMessage("suppression avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
       
              
    }

    @FXML
     private void comptee(ActionEvent event) {
         try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("compte.fxml"));
            
            Parent root=loader.load();
            cmpt.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    }
    