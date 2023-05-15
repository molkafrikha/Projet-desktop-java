/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.gui;

import edu.fithnitek.entities.Questions;
import edu.fithnitek.entities.Réponses;
import edu.fithnitek.entities.Sondage;
import edu.fithnitek.services.QuestionsCRUD;
import edu.fithnitek.services.RéponsesCRUD;
import edu.fithnitek.services.SondageCRUD;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import org.controlsfx.control.Rating;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SAHBI
 */
public class ReponsesController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane Transports;
    @FXML
    private TextField rep4;
    @FXML
    private TableView<Sondage> tableqr;
    @FXML
    private TableColumn<Sondage, String> sjt;
    @FXML
    private TableColumn<Sondage, String> Catégorie;
    @FXML
    private Button Affq;
    @FXML
    private TextField sujet;
     static Map<String, String[]> words = new HashMap<>();
    
    static int largestWordLength = 0;
  
    @FXML
    private TextField questRate;
    @FXML
    private Label msg;
    @FXML
    private TextField quest1;
    @FXML
    private ComboBox<String> rep1;
    @FXML
    private TextField quest3;
    @FXML
    private TextField quest2;
    @FXML
    private ComboBox<String> rep2;
    @FXML
    private ComboBox<String> rep3;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private TextField quest4;
    @FXML
    private Button btnpréc;

    RéponsesCRUD sr = new RéponsesCRUD();
    QuestionsCRUD sq = new  QuestionsCRUD();
    SondageCRUD ss= new SondageCRUD();
    @FXML
    private Rating Rate;
    

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("YES", "NO");
         rep1.setItems(list);
         rep2.setItems(list);
         rep3.setItems(list);
         
         ShowTable();
         addListenerForTable();
         
       
        // TODO
    }    
    
     

    @FXML
    private void EnvoyerRep(ActionEvent event) {
        
      
        
        String reponse1 =rep1.getValue();
        String reponse2 =rep2.getValue();
        String reponse3 =rep3.getValue();
        String reponse4 =rep4.getText();
       double reponseRate=Rate.getRating();
      String repRate=Double.toString(reponseRate);
        
        
        
        String q1 =quest1.getText();
        String q2 =quest2.getText();
        String q3 =quest3.getText();
        String q4=quest4.getText();
        String q5=questRate.getText();
        
         Questions s1 =sq.get(q1);
         Questions s2 =sq.get(q2);
         Questions s3 =sq.get(q3);
         Questions s4 =sq.get(q4);
         Questions s5 =sq.get(q5);
        
         Réponses r1 = new Réponses(s1,reponse1);
         Réponses r2 = new Réponses(s2,reponse2);
         Réponses r3 = new Réponses(s3,reponse3);
         Réponses r4 = new Réponses(s4,reponse4);
        Réponses r5 = new Réponses(s5,repRate);
         
       
         
         
      // if (!quest.equals("") && type.equals("") ){
         
       if( rep1.getValue().isEmpty()|| rep2.getValue().isEmpty() || rep3.getValue().isEmpty() ){       
     
                      
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Réponse");
            tr.setMessage("Erreur : Réponse non Envoyé");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));}
           // System.out.println("CV");
           
       else if (filterText(reponse4)==true){
       
          Stage stage = new Stage();
        
        Alert.AlertType type =Alert.AlertType.WARNING;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("This message was blocked because a bad word was found");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
//           
        }else{
             sr.ajouterEntitee(r1);
            sr.ajouterEntitee(r2);
            sr.ajouterEntitee(r3);
            sr.ajouterEntitee(r4);
          sr.ajouterEntitee(r5);
            

            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Réponse");
            tr.setMessage("Réponse envoyé avec succés ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
       }
        
        
        
    }
    
     private ObservableList<Sondage> getTableList() {
       
        ObservableList<Sondage> List = ss.listeDesEntites();
        return List ;
        
    }
    
    public void ShowTable() {
        ObservableList<Sondage> list = getTableList();
        tableqr.setItems(list);
        sjt.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        Catégorie.setCellValueFactory(new PropertyValueFactory<>("catégorie"));
        
        
        
       
    }
    
    private void addListenerForTable(){
        tableqr.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
           Affq.setDisable(false);
            
            sujet.setText(newselection.getSujet());
            
        }else{
               sujet.setText("");
               
               Affq.setDisable(true);
               
        }
        }); 
    
    }

     
    private void AfficherQ(ActionEvent event) {
        ShowTable();
    }
    
     private ObservableList<Questions> getTableList1() {
       
        ObservableList<Questions> List = sq.listeDesEntites();
        ObservableList<Questions> newList = 
        List.stream()
            .filter((Questions q)-> (q.getSondage().getSujet().equals(sujet.getText())))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            );
        //ObservableList<Questions> l=List.stream().filter((Questions q)-> (q.getSondage().getSujet().equals(sjt.getText()))).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return newList;   
    }

    @FXML
    private void afficher(ActionEvent event) {
        ObservableList<Questions> list = getTableList1().stream()
            .filter((Questions q)-> (q.getType().equals("YES/NO")))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            );
        
        String q1=list.get(0).getQuestion();
        String q2=list.get(1).getQuestion();
        String q3=list.get(2).getQuestion();
        
        
         ObservableList<Questions> list2 = getTableList1().stream()
            .filter((Questions q)-> (q.getType().equals("Rate")))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            );
         
        String qrate=list2.get(0).getQuestion();
        
        
        ObservableList<Questions> list3 = getTableList1().stream()
            .filter((Questions q)-> (q.getType().equals("Text")))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            );
        
        String q4=list3.get(0).getQuestion();
        
         quest1.setText(q1);
        quest2.setText(q2);
        quest3.setText(q3);
        quest4.setText(q4);
        questRate.setText(qrate);
               
}
     @FXML
    private boolean testQ4() {
             int nbNonChar = 0;
        for (int i = 1; i < rep4.getText().trim().length(); i++) {
            char ch = rep4.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && rep4.getText().trim().length() >= 3) {
          //  nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
           // nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
    
     
                       
    

    
    
   // Badwords
    
    public static void loadConfigs() {
        try {
           FileReader fr = new FileReader("C:\\Users\\SAHBI\\OneDrive\\Documents\\NetBeansProjects\\list-of-french-swear-words_csv-file.csv");
    	     BufferedReader reader = new BufferedReader(fr);
            String line = "";
            int counter = 0;
            while((line = reader.readLine()) != null) {
                counter++;
                String[] content = null;
                try {
                    content = line.split(",");
                    if(content.length == 0) {
                        continue;
                    }
                    String word = content[0];
                    System.out.println(word);
                    String[] ignore_in_combination_with_words = new String[]{};
                    System.out.println(ignore_in_combination_with_words);
                    if(content.length > 1) {
                        ignore_in_combination_with_words = content[1].split("_");
                    }

                    if(word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }
                    words.put(word.replaceAll(" ", ""), ignore_in_combination_with_words);
                    

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Loaded " + counter + " words to filter out");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Iterates over a String input and checks whether a cuss word was found in a list, then checks if the word should be ignored (e.g. bass contains the word *ss).
     * @param input
     * @return
     */
     
    public static ArrayList<String> badWordsFound(String input) {
        if(input == null) {
             System.out.println("pas de reponse");
            return new ArrayList<>();
           
        }

        // don't forget to remove leetspeak, probably want to move this to its own function and use regex if you want to use this 
        
        input = input.replaceAll("1","i");
        input = input.replaceAll("!","i");
        input = input.replaceAll("3","e");
        input = input.replaceAll("4","a");
        input = input.replaceAll("@","a");
        input = input.replaceAll("5","s");
        input = input.replaceAll("7","t");
        input = input.replaceAll("0","o");
        input = input.replaceAll("9","g");
        

        ArrayList<String> badWords = new ArrayList<>();
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");
        System.out.println(input);

        // iterate over each letter in the word
        for(int start = 0; start < input.length(); start++) {
            // from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached. 
            for(int offset = 1; offset < (input.length()+1 - start) && offset < largestWordLength; offset++)  {
                String wordToCheck = input.substring(start, start + offset);
                System.out.println(wordToCheck);
                if(words.containsKey(wordToCheck)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for(int s = 0; s < ignoreCheck.length; s++ ) {
                        if(input.contains(ignoreCheck[s])) {
                            ignore = true;
                            break;
                        }
                    }
                    if(!ignore) {
                        badWords.add(wordToCheck);
                    }
                }
            }
        }


        for(String s: badWords) {
            System.out.println(s + " qualified as a bad word in a username");
        }
        return badWords;

    }

    public static boolean filterText(String input) {
        loadConfigs();
        ArrayList<String> badWords = badWordsFound(input);
        if(badWords.size() > 0) {
            System.out.println("\"This message was blocked because a bad word was found. If you believe this word should not be blocked, please message support.\"");
            return true;
        }
        System.out.println("cv");
        return false;
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
