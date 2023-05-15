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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SAHBI
 */
public class AdminResController implements Initializable {

    @FXML
    private TableView<OffreCovoiturage> tabad;
    @FXML
    private TableColumn<OffreCovoiturage, String> lieu_d_ad;
    @FXML
    private TableColumn<OffreCovoiturage, String> lieu_a_ad;
    @FXML
    private TableColumn<OffreCovoiturage, Integer> nb_ad;
    @FXML
    private TableColumn<OffreCovoiturage, String> date_ad;
    @FXML
    private Button iddetail;
    @FXML
    private TableView<demandecovoiturage> tabad1;
    @FXML
    private TableColumn<demandecovoiturage, Integer> nb_placead;
    @FXML
    private TableColumn<demandecovoiturage, String> date_ad1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowTable() ;
        // TODO
    }    

    private ObservableList<OffreCovoiturage> getTableList() throws SQLException {
        OffreCovoiturageCRUD ts = new OffreCovoiturageCRUD();

        ObservableList<OffreCovoiturage> list = FXCollections.observableArrayList(ts.selectAll());

        return list;

    }
    private ObservableList<demandecovoiturage> getTableListDemande() throws SQLException {
       reservationCRUD  ts = new reservationCRUD();
        OffreCovoiturage offre = tabad.getSelectionModel().getSelectedItem();
         // demandecovoiturage dc = ts.getIdDC(offre.getId());
          int id = offre.getId();
         // System.out.println(id);
        ObservableList<demandecovoiturage> list = FXCollections.observableArrayList(ts.listeDesEntites());
       
        //System.out.println(list);
        ObservableList<demandecovoiturage> newList = 
        list.stream()
            .filter((demandecovoiturage c)-> (c.getOffre().getId()==id))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            );
    //    System.out.println(newList);
        return newList;    
      }
  
    public void ShowTable() {
       
      
        try {
            ObservableList<OffreCovoiturage> list = getTableList();
            tabad.setItems(list);
            // colid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
            lieu_d_ad.setCellValueFactory(new PropertyValueFactory<>("lieuD"));
            lieu_a_ad.setCellValueFactory(new PropertyValueFactory<>("lieuA"));
            nb_ad.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
            date_ad.setCellValueFactory(new PropertyValueFactory<>("dateD"));
        } catch (SQLException ex) {
            Logger.getLogger(AdminResController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
     public void ShowTableDetails() {
       
       
        try {
            ObservableList<demandecovoiturage> list = getTableListDemande();
            
            System.out.println(list);
            tabad1.setItems(list);
            // colid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
            date_ad1.setCellValueFactory(new PropertyValueFactory<>("dateReservation")); 
            nb_placead.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
        } catch (SQLException ex) {
            Logger.getLogger(AdminResController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
               
        
    }

    
    
    private void addListenerForTable(){
             demandecovoiturage d = new demandecovoiturage();
             reservationCRUD pcd = new reservationCRUD();
        tabad.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
               int id =newselection.getId();
              // System.out.println(id);
               demandecovoiturage obj = pcd.getIdDC(newselection.getId());
               System.out.println(obj);
            int nbp = pcd.getIdDC(newselection.getId()).getNbplace();
            //System.out.println(nbp);
           
           iddetail.setDisable(false);
          
         //  date.setValue(newselection.getDateD());
            
        }else{
              
              // date.setText("");
            iddetail.setDisable(true);
              
        }
        }); }

    @FXML
    private void show_details(ActionEvent event) {
        ShowTableDetails();
    }
//        try {
//             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/details_admin.fxml"));
//            Parent root = loader.load();
//           
//            Details_adminController dc = loader.getController();
//            OffreCovoiturage offre = tabad.getSelectionModel().getSelectedItem();
//
//            dc.showInformation(offre.getId());
//            
//            Scene scene = new Scene(root);
//            Stage stage =(Stage)iddetail.getScene().getWindow();
//            stage.setScene(scene);
//            //stage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());  
//        }
//    }
}
