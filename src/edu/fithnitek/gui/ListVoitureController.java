package edu.fithnitek.gui;
import javafx.scene.input.MouseEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Voiture;
import edu.fithnitek.services.VoitureCRUD;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javax.swing.Spring.width;
import java.time.LocalDate;
import java.util.Calendar;




public class ListVoitureController implements Initializable {

    @FXML
    private TableView<Voiture> voitureTable;
    @FXML
    private TableColumn<Voiture, String> matriculeT;
    @FXML
    private TableColumn<Voiture, String> marqueT;
    @FXML
    private TableColumn<Voiture, String> puissanceT;
    @FXML
    private TableColumn<Voiture, Integer> nbplaceT;
    @FXML
    private TableColumn<Voiture, Integer> kilometrageT;
    @FXML
    private TableColumn<Voiture, Date> derniereAT;
    @FXML
    private TableColumn<Voiture, Date> derniereVT;

    private VoitureCRUD voitureCRUD;
    
    private List<Voiture> voitureList;
    @FXML
    private Button midifierV;
    @FXML
    private Button add;
    @FXML
    private Button supprimerV;
    @FXML
    private TextField mat;
    @FXML
    private TextField marq;
    @FXML
    private TextField puiss;
    @FXML
    private TextField kilo;
    @FXML
    private TextField nbp;
    @FXML
    private DatePicker dateA;
    @FXML
    private DatePicker dateV;
    

    int index = -1;
    @FXML
    private TableColumn<Voiture, Integer> idV;
   
    @FXML
    private TextField idVoi;
    @FXML
    private Button maintenancee;
    @FXML
    private Button imprimer;
    @FXML
    private ColorPicker color;
    @FXML
    private TableColumn<Voiture, ColorPicker> colorT;
    
    public void UpdateTable(){
        
        idV.setCellValueFactory(new PropertyValueFactory<>("id"));
        matriculeT.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marqueT.setCellValueFactory(new PropertyValueFactory<>("marque"));
        puissanceT.setCellValueFactory(new PropertyValueFactory<>("puissance"));
        kilometrageT.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        nbplaceT.setCellValueFactory(new PropertyValueFactory<>("nbplaces"));
        derniereAT.setCellValueFactory(new PropertyValueFactory<>("dateAssurance"));

        derniereVT.setCellValueFactory(new PropertyValueFactory<>("dateDVidange"));
        colorT.setCellValueFactory(new PropertyValueFactory<>("color"));
        
        voitureList = voitureCRUD.getAll();
        voitureTable.setItems(FXCollections.observableArrayList(voitureList));
        
    }

    @Override
     public void initialize(URL url, ResourceBundle rb) {
    voitureCRUD = new VoitureCRUD(); 
    voitureTable.getColumns().addAll(idV ,matriculeT, marqueT, puissanceT, nbplaceT, kilometrageT, derniereAT, derniereVT , colorT);
    voitureTable.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() > 1) {
            Voiture voiture = voitureTable.getSelectionModel().getSelectedItem();
            remplirChamps(voiture);
        }
    });
    UpdateTable();
    // Configurer les colonnes pour afficher les champs appropriés de l'objet Voiture
}
      
   
         
         
       public void Edit() {
    try {
        // Get the values from the input fields
        String id = idVoi.getText();
        String matricule = mat.getText();
        String marque = marq.getText();
        String puissance = puiss.getText();
        String nbplaces = nbp.getText();
        int kilometrage = Integer.parseInt(kilo.getText());
        Date dateDVidange = java.sql.Date.valueOf(dateV.getValue());
        Date dateAssurance = java.sql.Date.valueOf(dateA.getValue());
        javafx.scene.paint.Color colorValue = color.getValue();
        String colorString = String.format("#%02X%02X%02X", (int) (colorValue.getRed() * 255),
            (int) (colorValue.getGreen() * 255), (int) (colorValue.getBlue() * 255));
        
        // Validate the input fields
        if (id.isEmpty() || matricule.isEmpty() || marque.isEmpty() || puissance.isEmpty() ||
                nbplaces.isEmpty() || dateDVidange == null || dateAssurance == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sql = "UPDATE voiture SET matricule = ?, marque = ?, puissance = ?, nbplaces = ?, " +
            "kilometrage = ?, dateAssurance = ?, dateDVidange = ?, color = ? WHERE id = ?";
        
        // Create a connection to the database
        PreparedStatement pstSelect = MyConnection.getInstance().getCnx().prepareStatement(sql);
        
        // Prepare a parameterized update query
        
        pstSelect.setString(1, matricule);
        pstSelect.setString(2, marque);
        pstSelect.setString(3, puissance);
        pstSelect.setString(4, nbplaces);
        pstSelect.setInt(5, kilometrage);
        pstSelect.setDate(6, new java.sql.Date(dateAssurance.getTime()));
        pstSelect.setDate(7, new java.sql.Date(dateDVidange.getTime()));
        pstSelect.setString(8, colorString);
        pstSelect.setString(9, id);
        
        // Execute the update query
        int rowsUpdated = pstSelect.executeUpdate();
        
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Voiture mise à jour avec succès");
            UpdateTable();
        } else {
            JOptionPane.showMessageDialog(null, "Échec de la mise à jour de la voiture", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide pour le kilométrage", "Erreur", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erreur SQL : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    
                 
             }
         
       }

 
    
    @FXML
        public void  add (){

      try {
    // Check if any field is empty or null
    if (mat.getText().isEmpty() || marq.getText().isEmpty() || puiss.getText().isEmpty() || kilo.getText().isEmpty() || nbp.getText().isEmpty() || dateA.getValue() == null || dateV.getValue() == null) {
        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs du formulaire !");
        return;
    }
    
    String sqlSelect = "SELECT COUNT(*) FROM voiture WHERE matricule = ?";
    PreparedStatement pstSelect = MyConnection.getInstance().getCnx().prepareStatement(sqlSelect);
    pstSelect.setString(1, mat.getText());
    ResultSet rs = pstSelect.executeQuery();
    rs.next();
    int count = rs.getInt(1);
    if (count > 0) {
        JOptionPane.showMessageDialog(null, "Cette voiture existe déjà !");
        return;
    }
    
    String sql="INSERT INTO voiture (matricule , marque , puissance, kilometrage, nbplaces, dateAssurance , dateDVidange , color , idUser ,idagence)"+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
    Date date = java.sql.Date.valueOf(dateA.getValue());
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    
    Date datee = java.sql.Date.valueOf(dateV.getValue());
    java.sql.Date sqlDatee = new java.sql.Date(datee.getTime());
    int idUser = 55;
    int idagence = 2;
    pst.setString(1, mat.getText());
    pst.setString(2, marq.getText());
    pst.setString(3, puiss.getText());
    pst.setString(4 ,kilo.getText());
    pst.setString(5, nbp.getText());
    pst.setDate(6, sqlDate);
    pst.setDate( 7 ,sqlDatee);
    
    javafx.scene.paint.Color colorr = color.getValue();
        String hex = String.format("#%02X%02X%02X", (int)(colorr.getRed()*255), (int)(colorr.getGreen()*255), (int)(colorr.getBlue()*255));
        pst.setString(8, hex);
        pst.setInt (9, idUser );
        pst.setInt (10, idagence );
    pst.executeUpdate();
    System.out.println("Done!");
    JOptionPane.showMessageDialog(null, "Voiture ajoutée avec succès !");
    UpdateTable();
} catch (SQLException e) {
     System.out.println(e.getMessage());
}
        
    }
        
    @FXML
     public void Delete() throws SQLException{
    int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le produit N°" + idVoi.getText() + " ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
    if (choice != JOptionPane.YES_OPTION) {
        return;
    }
    
    String sql="DELETE FROM voiture WHERE id=?";
    try {
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
        pst.setString(1,idVoi.getText());
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected == 0) {
            JOptionPane.showMessageDialog(null, "ID does not exist!");
        } else {
            JOptionPane.showMessageDialog(null, "Deleted!");
            UpdateTable();
        }
    }
    catch (HeadlessException | SQLException e) {
        // handle errors here
    }   
}
     
     
     
    @FXML
     public void maintenance_view(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("MainVoiture.fxml"));
            
            Parent root=loader.load();
            maintenancee.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
         
       
     }
     public void offreo_view(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("OffreCovoiturage.fxml"));
            
            Parent root=loader.load();
            maintenancee.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
     }
     public void Sondage(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Reponses.fxml"));
            
            Parent root=loader.load();
            maintenancee.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
      public void compte(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("compte.fxml"));
            
            Parent root=loader.load();
            maintenancee.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
        public void reservation(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("UserRes.fxml"));
            
            Parent root=loader.load();
            maintenancee.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
     
    

    @FXML
    private void handlePdfButton(ActionEvent event) throws IOException {
               com.itextpdf.text.Document document = new com.itextpdf.text.Document();
              try {
            PdfWriter.getInstance(document, new FileOutputStream("voiture.pdf"));
            
            document.open();
            document.addTitle("Titre du document");
            // Create a BaseColor instance with RGB values
//BaseColor myColor = new BaseColor(150, 12, 172);

// Set the background color for the document

Font titleFont = new Font(Font.FontFamily.HELVETICA, 28, Font.BOLD, BaseColor.WHITE);
Paragraph title = new Paragraph("Liste des voitures", titleFont);
title.setAlignment(Element.ALIGN_CENTER);
title.setSpacingAfter(30); // Ajouter un espace après le titre
title.setIndentationLeft(50); // Définir une indentation à gauche pour centrer le titre
title.setIndentationRight(50); // Définir une indentation à droite pour centrer le titre
document.add(title);
            PdfPTable table = new PdfPTable(8); // 3 columns

            // Add table headers
            
            table.addCell("ID");
            table.addCell("matricule");
            table.addCell("marque ");
            table.addCell("puissance ");
            table.addCell("kilometrage ");
            table.addCell("nombre de place");
            table.addCell("date derniere assurance");
            table.addCell("date derniere vidange ");
                        Connection conn = MyConnection.getInstance().getCnx();
            // Add table rows from the database
            String query = "SELECT * FROM voiture";
            ResultSet resultSet = conn.createStatement().executeQuery(query);
            
  
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String matricule = resultSet.getString("matricule");
                String marque = resultSet.getString("marque");
                String puissance = resultSet.getString("puissance");
                int kilometrage = resultSet.getInt("kilometrage");
                int nbplaces = resultSet.getInt("nbplaces");
                int dateassurance = resultSet.getInt("dateAssurance");
                int datevidange = resultSet.getInt("dateDVidange");
               
                table.addCell(Integer.toString(id));   //ism les attribut ml base 
                table.addCell(matricule);
                table.addCell(marque);
                table.addCell(puissance);
                table.addCell(Integer.toString(kilometrage));
                table.addCell(Integer.toString(nbplaces));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                table.addCell(formatter.format(dateassurance));
                table.addCell(formatter.format(datevidange));
            }
            document.add(table);
            document.close();
            Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler voiture.pdf");
JOptionPane.showMessageDialog(null, "Les voitures ont été exportées dans le fichier voiture.pdf");
        } catch (FileNotFoundException | DocumentException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'exportation des données des emplois : " + e.getMessage());
        }


            
         

    }
    private void remplirChamps(Voiture voiture) {
    idVoi.setText(String.valueOf(voiture.getId_voiture()));
    mat.setText(voiture.getMatricule());
    marq.setText(voiture.getMarque());
    puiss.setText(voiture.getPuissance());
    nbp.setText(String.valueOf(voiture.getNbplaces()));
    kilo.setText(String.valueOf(voiture.getKilometrage()));
    Calendar calAssurance = Calendar.getInstance();
    calAssurance.setTime(voiture.getDateAssurance());
    LocalDate dateAssurance = LocalDate.of(calAssurance.get(Calendar.YEAR),
                                            calAssurance.get(Calendar.MONTH) + 1,
                                            calAssurance.get(Calendar.DAY_OF_MONTH));
    dateA.setValue(dateAssurance);
    
    Calendar calVidange = Calendar.getInstance();
    calVidange.setTime(voiture.getDateDVidange());
    LocalDate dateVidange = LocalDate.of(calVidange.get(Calendar.YEAR),
                                          calVidange.get(Calendar.MONTH) + 1,
                                          calVidange.get(Calendar.DAY_OF_MONTH));
    dateV.setValue(dateVidange);
    
    color.setValue(javafx.scene.paint.Color.web(voiture.getColor()));
}
    
}






            

    
