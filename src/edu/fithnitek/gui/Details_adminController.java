package edu.fithnitek.gui;

import edu.fithnitek.entities.OffreCovoiturage;
import edu.fithnitek.entities.demandecovoiturage;
import edu.fithnitek.services.OffreCovoiturageCRUD;
import edu.fithnitek.services.reservationCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;

/**
 * FXML Controller class
 *
 * @author SAHBI
 */
public class Details_adminController implements Initializable {
   
    @FXML
    private TextField sjt;
    @FXML
    private TableView<demandecovoiturage> tabad;
    @FXML
    private TableColumn<demandecovoiturage, Integer> nb_placead;
    @FXML
    private TableColumn<demandecovoiturage, String> date_ad;
    @FXML
    private Button afficher;

    /**
     * Initializes the controller class.
     */
     
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
                        
        
//                reservationCRUD  ts = new reservationCRUD();
//                 System.out.println(ts.listeDesEntites());
//         
      
    }
    private ObservableList<demandecovoiturage> getTableList() throws SQLException {
        reservationCRUD  ts = new reservationCRUD();

        ObservableList<demandecovoiturage> list = FXCollections.observableArrayList(ts.listeDesEntites());
        String s = sjt.getText();
        int id =Integer.parseInt(s);
       // ObservableList<demandecovoiturage> newList = 
//        list.stream()
//            .filter((demandecovoiturage d)-> (ts.getIdDC(7)))
//            .collect(Collector.of(
//                FXCollections::observableArrayList,
//                ObservableList::add,
//                (l1, l2) -> { l1.addAll(l2); return l1; })
//            );
//        System.out.println(newList);
        //ObservableList<Questions> l=List.stream().filter((Questions q)-> (q.getSondage().getSujet().equals(sjt.getText()))).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return list;       
        
    }
    
//    private ObservableList<demandecovoiturage> getTableList() throws SQLException {
//        reservationCRUD ts = new reservationCRUD();
//
//        ObservableList<demandecovoiturage> list = FXCollections.observableArrayList(ts.listeDesEntites());
//
//        return list;
//
//    }
     public void showInformation(int id){
        sjt.setText(Integer.toString(id));
    }

        
    public void ShowTable() {
       
        try {
            ObservableList<demandecovoiturage> list = getTableList();
            tabad.setItems(list);
            // colid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
            nb_placead.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
            
            date_ad.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));    
        } catch (SQLException ex) {
            Logger.getLogger(Details_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficher(ActionEvent event) {
        
    }

    @FXML
    private void afficherD(InputMethodEvent event) {
        ShowTable();
    }
    
}


