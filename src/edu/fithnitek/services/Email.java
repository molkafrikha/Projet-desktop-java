/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

/**
 *
 * @author T480s
 */
import edu.fithnitek.entities.User;
import edu.fithnitek.entities.Verification;
import edu.fithnitek.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
 
 

public void sendEmail(String toEmail, String subject, String messageText) {

    // Set the Gmail SMTP server properties
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    // Authenticate with the Gmail SMTP server using your Gmail account credentials
    Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aicha.nciri@esprit.tn", "223JFT5664");
            }
        });

    try {
        // Create the email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("aicha.nciri@esprit.tn"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(messageText);

        // Send the email message
        Transport.send(message);

        System.out.println("Email sent to " + toEmail);

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}


  public Boolean isValidCode(String code) throws SQLException {
      
      String query = "SELECT * FROM verification WHERE code = ?";
      PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
        pst.setString(1, code);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String codeValue = rs.getString("code");
            Verification verifcation = new Verification(id,codeValue);
            return true;
} else{
        return false ;
  }
        
        
  }
 
  
      public int generateCodeAndSaveInDataBase(){
             Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; 
        
              try {
            String requete = "INSERT INTO verification(code)"
                    + "VALUES (?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1,String.valueOf(randomNumber));
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return randomNumber ;          
    }
    
}
