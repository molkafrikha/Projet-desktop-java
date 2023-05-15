package edu.fithnitek.gui;

import edu.fithnitek.entities.User;
import edu.fithnitek.services.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListeDesUtilisateursController implements Initializable {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> idCol;
    @FXML
    private TableColumn<User, String> nomCol;
    @FXML
    private TableColumn<User, String> prenomCol;
    private ObservableList<User> userList;
    @FXML
    private TableColumn<User, Void> actionsColumn;
    @FXML
    private Button aviss;
    @FXML
    private Button recla;
    @FXML
    private Button event1;
    @FXML
    private Button deco;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserCRUD pcd= new UserCRUD();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        userList = FXCollections.observableArrayList(pcd.getAllUsers());
        userTable.setItems(userList);
        
        actionsColumn.setCellFactory(param -> new TableCell<User, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
             deleteButton.setOnAction(event -> {
    Object selectedItem = getTableRow().getItem();
    if (selectedItem instanceof User) {
        User user = (User) selectedItem;
        if (user != null) {
            UserCRUD pcd = new UserCRUD();
            pcd.supprimerEntity(user);
            userList.remove(user);
        }
    }
});

            }     

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setGraphic(deleteButton);
                } else {
                    setGraphic(null);
                }
            }
        });

        // Load the list of users
   
    }   
    @FXML
      public void avis(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AvisAdmin.fxml"));
            
            Parent root=loader.load();
            aviss.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
    @FXML
        public void recla(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("ReclamationBack.fxml"));
            
            Parent root=loader.load();
            recla.getScene().setRoot(root);
            
           
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
    @FXML
         public void eventt(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("ListeEvenements.fxml"));
            
            Parent root=loader.load();
           event1.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
          @FXML
         public void dec(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
            
            Parent root=loader.load();
           deco.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
 @FXML
         public void sondage(ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Sondage.fxml"));
            
            Parent root=loader.load();
           deco.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }
 @FXML
         public void Reservation (ActionEvent event){
         
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AdminRes.fxml"));
            
            Parent root=loader.load();
           deco.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
   
     }


}
