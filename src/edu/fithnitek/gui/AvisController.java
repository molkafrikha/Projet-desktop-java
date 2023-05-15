/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;


import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import edu.fithnitek.entities.Avis;
import static edu.fithnitek.gui.ReclamationController.listR;
import edu.fithnitek.services.AvisCRUD;




/**
 * FXML Controller class
 *
 * @author Nihel
 */
public class AvisController implements Initializable {

    @FXML
    private TableColumn<?, ?> id_utilisateur;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> id_commentaire;

    AvisCRUD rc = new AvisCRUD();
    public static ObservableList<Avis> listA = null;
    @FXML
    private TableView<Avis> table;
    @FXML
    private TextArea commentaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayAvis();
    }
 
    
    public void displayAvis() {

        ImageView imageView = new ImageView(getClass().getResource("/edu/fithnitek/image/ajouter.png").toExternalForm());
        ajouter.setGraphic(imageView);
        ImageView imageView1 = new ImageView(getClass().getResource("/edu/fithnitek/image/supprimer.png").toExternalForm());
        supprimer.setGraphic(imageView1);

        id.setCellValueFactory(new PropertyValueFactory("id"));
        id_commentaire.setCellValueFactory(new PropertyValueFactory("commentaire"));
        id_utilisateur.setCellValueFactory(new PropertyValueFactory("id_usr"));

        List l = rc.selectAll();

        listA = FXCollections.observableArrayList(l);

        table.setItems(listA);

    }

    @FXML
    private void ajouterAvis(ActionEvent event) {
        String s = commentaire.getText();

        if (!(commentaire.getText().equals(""))) {
            Avis a = new Avis();
            a.setCommentaire(s);
            a.setId_usr(55);
            
            rc.create(a); 
            
            displayAvis();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("Verifier vos données ");
            alert.showAndWait();
        }
    }

    @FXML
    private void supprimerAvis(ActionEvent event) {
        Avis rec = (Avis) table.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Voulez-vous vraiment supprimer l'avis N°" + rec.getId());
        a.setTitle("CONFIRMER");
        Optional<ButtonType> res = a.showAndWait();
        if (res.get() == ButtonType.OK) {
            if (rc.delete(rec.getId())) {
                listA.remove(rec);
            }
        }
    }
}
