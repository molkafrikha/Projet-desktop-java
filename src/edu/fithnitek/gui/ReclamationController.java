/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

//import com.monkeylearn.MonkeyLearn;
import java.awt.Image;
import static java.lang.System.gc;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edu.fithnitek.entities.Reclamation;
import edu.fithnitek.services.ReclamationCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Nihel
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button Reclamer1;
    @FXML
    private TextArea description;
    @FXML
    private TextField objet;
    @FXML
    private TableView table_reclamation;
    @FXML
    private TableColumn tb_id;
    @FXML
    private TableColumn tb_intitule;
    @FXML
    private TableColumn tb_contenu;
    @FXML
    private TableColumn id_usr;
    @FXML
    private Button supprimer;

    ReclamationCRUD rc = new ReclamationCRUD();
    public static ObservableList<Reclamation> listR = null;
    @FXML
    private Label count;
    @FXML
    private Button compt;
     @FXML
    private TableColumn date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayReclamation();
        try {
            count.setText(String.valueOf(rc.count()));
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static final String ACCOUNT_SID = "AC033be2e8b7aae4ddc010f0c2c25d6b25";
    public static final String AUTH_TOKEN = "2fd0abb047e9efe1a92ee80b5379d3b0";

    public void displayReclamation() {

        ImageView imageView = new ImageView(getClass().getResource("/edu/fithnitek/image/supprimer.png").toExternalForm());
        supprimer.setGraphic(imageView);

        tb_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_intitule.setCellValueFactory(new PropertyValueFactory("intitule"));
        tb_contenu.setCellValueFactory(new PropertyValueFactory("contenu"));
        id_usr.setCellValueFactory(new PropertyValueFactory("id_usr"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        List l = rc.selectAll();

        listR = FXCollections.observableArrayList(l);

        table_reclamation.setItems(listR);

    }

    private String filterText(String text) {
        // Les mots à filtrer
        String[] filteredWords = {"mauvais", "mot", "test", "débile", "bete", "stupid", "un con"};

        for (String word : filteredWords) {
            String replacement = "";
            for (int i = 0; i < word.length(); i++) {
                replacement += "*";
            }
            text = text.replaceAll("(?i)\\b" + word + "\\b", replacement);
        }

        return text;
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
        String s = objet.getText();
        String s1 = filterText(description.getText());

        if (!(description.getText().equals("")) && !(objet.getText().equals(""))) {
            Reclamation r = new Reclamation();
            r.setIntitule(s);
            r.setContenu(s1);
            r.setId_usr(1);
            
            
            rc.create(r);
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber("+21654931161"), new PhoneNumber("+12764962723"), "Votre récalamation " + description.getText() + " est envoyée avec succées !").create();
            displayReclamation();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("Verifier vos données ");
            alert.showAndWait();
        }

    }

    @FXML
    private void DeleteReclamation(ActionEvent event) {
        Reclamation rec = (Reclamation) table_reclamation.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Voulez-vous vraiment supprimer la réclamation N°" + rec.getId());
        a.setTitle("CONFIRMER");
        Optional<ButtonType> res = a.showAndWait();
        if (res.get() == ButtonType.OK) {
            if (rc.delete(rec.getId())) {
                listR.remove(rec);
            }
        }
    }
     @FXML
         public void comptt(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("compte.fxml"));
            
            Parent root=loader.load();
           compt.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }

    

}
