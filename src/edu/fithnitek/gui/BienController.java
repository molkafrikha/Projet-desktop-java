/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import static com.teamdev.jxmaps.internal.Browser.a.b;
import com.teamdev.jxmaps.t;
import edu.fithnitek.entities.Bien;
import edu.fithnitek.entities.CalculeDistance;
import edu.fithnitek.services.BienCRUD;
import edu.fithnitek.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * FXML Controller class
 *
 * @author Nadhem
 */
public class BienController extends CalculeDistance implements Initializable {

    @FXML
    private DatePicker dated;
    @FXML
    private Button b_ajouter;
    @FXML
    private Button b_modifier;
    @FXML
    private Button b_supprimer;
    private List<Bien> trbienList;
    @FXML
    private TextField lieud;
    @FXML
    private TextField lieua;
    @FXML
    private TableView<Bien> table;
    @FXML
    private TableColumn<Bien, String> tdepart;
    @FXML
    private TableColumn<Bien, String> tarriver;
    @FXML
    private TableColumn<Bien, LocalDate> tdate;
    @FXML
    private TableColumn<Bien, String> tnum;
    @FXML
    private TextField num;
    @FXML
    private Button b2_offre;
    @FXML
    private Button b2_bien;
    private Button b2_loc;
    @FXML
    private Button b_compte;
    @FXML
    private Button b_maintenace;
    @FXML
    private Button b_reclamation;
    @FXML
    private Button b_voiture;
    @FXML
    private Button b_evenement;
    @FXML
    private Button b_sondage;
    @FXML
    private Button b_reserver;

    public void UpdateTable() {
       ObservableList<Bien> bienList = FXCollections.observableArrayList();
        try {
            
            String query = "SELECT * FROM Bien";
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bien bien = new Bien(
                        resultSet.getInt("id"),
                        resultSet.getString("lieud"),
                        resultSet.getString("lieua"),
                        resultSet.getDate("dated"),
                        resultSet.getString("num"));
                bienList.add(bien);
                
                
                //table.getColumns().addAll(tdepart ,tarriver, , tdate, tnum);
        
            }
            //ystem.out.println(bienList);
        } catch (SQLException ex) {
            System.out.println("Error while retrieving data from database: " + ex.getMessage());
            ex.printStackTrace();
        }
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tdepart.setCellValueFactory(new PropertyValueFactory<>("lieud"));
        tarriver.setCellValueFactory(new PropertyValueFactory<>("lieua"));
        tdate.setCellValueFactory(new PropertyValueFactory<>("dated"));
        tnum.setCellValueFactory(new PropertyValueFactory<>("num"));
        table.setItems(bienList);
    } 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
       /* table.setOnMouseClicked((MouseEvent e) -> {
            if (e.getClickCount() == 2) {
                Bien offreCovoiturage = table.getSelectionModel().getSelectedItem();
                if (offreCovoiturage != null) {
                    //id11.setText(Integer.toString(bien.getId()));
                    tdepart.setText(offreCovoiturage.getLieud());
                    tarriver.setText(offreCovoiturage.getLieua());
                    Calendar calVidange = Calendar.getInstance();
                    calVidange.setTime(offreCovoiturage.getDated());
                    LocalDate dateDepart = LocalDate.of(calVidange.get(Calendar.YEAR),
                            calVidange.get(Calendar.MONTH) + 1,
                            calVidange.get(Calendar.DAY_OF_MONTH));
                    tdate.setValue(dateDepart);
                    tnum.setText(String.bien.getNum());
                }
            }
        }); */

    }
    @FXML
    private void ajouter(ActionEvent event) {
        try {
            if (lieud.getText().isEmpty() || lieua.getText().isEmpty() || dated.getValue() == null || num.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Tous les champs sont obligatoires");
            } else {
                double latdepart = 0;
                double londepart = 0;
                double latarriver = 0;
                double lonarriver = 0;

                String sql = "INSERT INTO Bien(lieud, lieua, dated, num)"
                        + "values(?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);

                java.util.Date datee = java.sql.Date.valueOf(dated.getValue());
                java.sql.Date sqlDatee = new java.sql.Date(datee.getTime());

                pst.setString(1, lieud.getText());
                pst.setString(2, lieua.getText());
                 pst.setObject(3, datee);
                  pst.setInt(4, Integer.parseInt(num.getText()));
                
                pst.executeUpdate();
                System.out.println("Done!");
                UpdateTable();
               Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("A");
            alert.setHeaderText(null);
            alert.setContentText("Ajouté avec succées");
            alert.showAndWait();
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void offre() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreCovoiturage.FXML"));
        Parent root = loader.load();
        Scene scene = b2_offre.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    }

    @FXML
    private void bien() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bien.FXML"));
        Parent root = loader.load();
        Scene scene = b2_bien.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    }

    private void map() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Localisation.FXML"));
        Parent root = loader.load();
        Scene scene = b2_loc.getScene();
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
    }
    @FXML
    private void deleteBien(ActionEvent event) {
        // Vérifier si une offre a été sélectionnée
        Bien bien = table.getSelectionModel().getSelectedItem();
        if (bien == null) {
            // Afficher un message d'erreur si aucune offre n'a été sélectionnée
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune bien sélectionnée");
            alert.setContentText("Veuillez sélectionner un bien.");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de la suppression");
        alert.setHeaderText("Supprimer le bien sélectionnée ?");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer le bien sélectionnée ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            
            BienCRUD bienCRUD = new BienCRUD();
            bienCRUD.delete(bien);

            // Afficher un message de succès
            Alert alertSuccess = new Alert(AlertType.INFORMATION);
            alertSuccess.setTitle("Succès");
            alertSuccess.setHeaderText("Bien supprimée avec succès");
            UpdateTable();
            alertSuccess.showAndWait();

           //UpdateTable();
        }
  
}
     @FXML
    private void updateBien(ActionEvent event) {
        BienCRUD BR=new BienCRUD();
        Bien b;
        b=table.getSelectionModel().getSelectedItem();
            String ld = lieud.getText();
            String la = lieua.getText();
            Date dateee = Date.valueOf(dated.getValue());
            String numtell = num.getText();
        
         
            b.setLieua(ld);
            b.setLieud(la);
            b.setDated(dateee);
            b.setNum(numtell);
            
            BR.update(b, ld,la,dateee,numtell);
            
            UpdateTable();
            
    }    
}
   