package edu.fithnitek.gui;



import org.controlsfx.control.Notifications;
//package edu.voiture.gui;
import javafx.scene.paint.Color;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Voiture;
import edu.fithnitek.entities.maintenance;
import edu.fithnitek.services.VoitureCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainVoitureController implements Initializable {

    @FXML
    private TableColumn<maintenance, Integer> id_Maint;
    @FXML
    private TableColumn<maintenance, String> Matvoiture;
    @FXML
    private TableColumn<maintenance, Date> dateDMV;
    @FXML
    private TableColumn<maintenance, Date> dateDPA;
    @FXML
    private TableColumn<maintenance, Date> dateDVV;
    @FXML
    private ComboBox<String> idcombo;
    @FXML
    private DatePicker dda;
    @FXML
    private DatePicker ddv;
    @FXML
    private Button idajouter;
    @FXML
    private TextField kilom;
    private VoitureCRUD voitureCRUD;
    @FXML
    private TableView<maintenance> maintable;
    private List<maintenance> mainList;
    @FXML
    private Button voituree;
    @FXML
    private ColorPicker color;
    @FXML
    private TableColumn<maintenance, Integer> reskilo;
    
    
    @FXML
    private Button notification;
    @FXML
    private AnchorPane notificationB;


    /**
     * Initializes the controller class.
     */ 
  
    
    
    public void UpdateTableM(){
         
         
       
        id_Maint.setCellValueFactory(new PropertyValueFactory<>("id_maintenance"));
        Matvoiture.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        dateDMV.setCellValueFactory(new PropertyValueFactory<>("dateDAssurance"));
        dateDPA.setCellValueFactory(new PropertyValueFactory<>("datePAssurance"));
        dateDVV.setCellValueFactory(new PropertyValueFactory<>("dateDVidange"));
        reskilo.setCellValueFactory(new PropertyValueFactory<>("restekilometre"));
        mainList = voitureCRUD.getmaintenance();
        maintable.setItems(FXCollections.observableArrayList(mainList));
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      voitureCRUD = new VoitureCRUD();
       maintable.getColumns().addAll(id_Maint, Matvoiture, dateDMV, dateDPA, dateDVV, reskilo);
        System.out.println(maintable);
      UpdateTableM();
    try {
        String query = "SELECT matricule FROM voiture";
        PreparedStatement pstSelect = MyConnection.getInstance().getCnx().prepareStatement(query);
        ResultSet resultSet = pstSelect.executeQuery();
        
        while (resultSet.next()) {
            String matricule = resultSet.getString("matricule");
            idcombo.getItems().add(matricule);
        }
    } catch (SQLException ex) {
        Logger.getLogger(MainVoitureController.class.getName()).log(Level.SEVERE, null, ex);
    }

    
    UpdateTableM();
    idcombo.setOnAction(event -> {
    String matricule = idcombo.getValue();
    try {
        String query = "SELECT kilometrage ,dateAssurance , dateDVidange , color  FROM Voiture WHERE matricule = ?";
        PreparedStatement pstSelect = MyConnection.getInstance().getCnx().prepareStatement(query);
        pstSelect.setString(1, matricule);
        ResultSet resultSet = pstSelect.executeQuery();
        
        if (resultSet.next()) {
            int kilo = resultSet.getInt("kilometrage");
            
            java.sql.Date dateA = resultSet.getDate("dateAssurance");
            java.sql.Date dateV = resultSet.getDate("dateDVidange");
            
            
            
            
            // Faire quelque chose avec les informations récupérées
            // Par exemple, remplir des champs de texte dans l'interface graphique
            kilom.setText(String.valueOf(kilo));
            dda.setValue(dateA.toLocalDate());
            ddv.setValue(dateV.toLocalDate());
            String colorStr = resultSet.getString("color");
            Color colorObj = Color.valueOf(colorStr);
            color.setValue(colorObj);
            
            
            
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(MainVoitureController.class.getName()).log(Level.SEVERE, null, ex);
    }
}); 
    
    
  
    }
    
   
    
    @FXML
     public void  add (){

      try {
    // Check if any field is empty or null
    if ( kilom.getText().isEmpty() ||  dda.getValue() == null || ddv.getValue() == null) {
        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs du formulaire !");
        return;
    }
    
    String sqlSelect = "SELECT COUNT(*) FROM maintenance WHERE matricule = ?";
          System.out.println(sqlSelect);
    PreparedStatement pstSelect = MyConnection.getInstance().getCnx().prepareStatement(sqlSelect);
    pstSelect.setString(1, idcombo.getValue());
    ResultSet rs = pstSelect.executeQuery();
    rs.next();
    int count = rs.getInt(1);
    if (count > 0) {
        JOptionPane.showMessageDialog(null, "Cette voiture existe déjà !");
        return;
    }
    
    
    String sql = "INSERT INTO maintenance ( matricule ,dateDAssurance,datePAssurance, dateDVidange ,restekilometre  )"+ "VALUES (?,?,?,?,?)";
    PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
    
    int kilo = Integer.parseInt(kilom.getText());
    int restekilo = 10000 - kilo;
    LocalDate dateDebutAssurance = dda.getValue();
    LocalDate dateDebutVidange = ddv.getValue();
    
    
 
     LocalDate dateFinAssurance = dateDebutAssurance.plus(1, ChronoUnit.YEARS);
     //

    
    pst.setString(1, idcombo.getValue());
    pst.setDate(2, java.sql.Date.valueOf(dateDebutAssurance));
    pst.setDate(3, java.sql.Date.valueOf(dateFinAssurance));
    pst.setDate(4, java.sql.Date.valueOf(dateDebutVidange));
    pst.setInt(5 , restekilo);
    
    //pst.setString(5, dpv.getText());
    
    pst.executeUpdate();
    System.out.println("Done!");
    JOptionPane.showMessageDialog(null, "Voiture ajoutée avec succès !");
   
} catch (SQLException e) {
     System.out.println(e.getMessage());
}
       UpdateTableM(); 
    }
    
    
    @FXML
    public void voiture_view(ActionEvent event){
         
        try {
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ListVoiture.fxml"));
            Parent root=loader.load();
            voituree.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }

     }
     public void offreo_view(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("OffreCovoiturage.fxml"));
            
            Parent root=loader.load();
            voituree.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
     }
     public void Sondage(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Reponses.fxml"));
            
            Parent root=loader.load();
            voituree.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
      public void compte(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("compte.fxml"));
            
            Parent root=loader.load();
            voituree.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
        public void reservation(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("UserRes.fxml"));
            
            Parent root=loader.load();
            voituree.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
    
    @FXML
    public void handleSaveButton(ActionEvent event) {
        
        String procheAssurance = "";
    try {
         String sqlSelect = "SELECT * FROM maintenance WHERE DATEDIFF(datePAssurance, NOW()) < 7";
        // Exécutez la requête SQL pour récupérer les voitures dont la date d'assurance est très proche
        PreparedStatement pstSelect = MyConnection.getInstance().getCnx().prepareStatement(sqlSelect);

        ResultSet rs = pstSelect.executeQuery();
        
        // Parcourez les résultats de la requête et créez une chaîne de caractères
        while (rs.next()) {
            procheAssurance += rs.getString("matricule") + "\n";
        }
    } catch (SQLException e) {
    }
    if (!procheAssurance.isEmpty()) { 
        
    Notifications notificationBuilder = Notifications.create()
            .title("le ID Voiture avec date d'assurance très proche :")
            .text(procheAssurance)
            .hideAfter(Duration.seconds(6))
            .position((Pos.BOTTOM_RIGHT));
    
            notificationBuilder.show();
        
    }
    else {
        Notifications notificationBuilder = Notifications.create()
            .title("le ID de Voiture avec date d'assurance très proche :")
            .text("aucune voiture avec date d'assurance très proche ! ")
            .hideAfter(Duration.seconds(6))
            .position((Pos.BOTTOM_RIGHT));
    
            notificationBuilder.show();
    }
        
}

    
}
    
    
   




