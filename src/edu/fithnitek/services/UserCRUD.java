/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

import edu.fithnitek.entities.Role;
import edu.fithnitek.entities.User;
import edu.fithnitek.utils.MyConnection;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author T480s
 */
public class UserCRUD {

    public void ajouterEntitee(User user) {
        try {
            String requete = "INSERT INTO user( nom, prenom,email,password, roles, age)"
                    + "VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, user.getNom());
            pst.setString(2, user.getPrenom());
            pst.setString(3, user.getEmail());
            pst.setString(4, PasswordHash.hashPassword(user.getPassword()));
            pst.setString(5, user.getRoles());
            pst.setInt(6, user.getAge());
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerEntity(User user) {
        try {
            String requete = "DELETE FROM user WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setInt(1, user.getId());
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Boolean isUserExist(String email) throws SQLException {
        Boolean isExist = false;
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            isExist = true;
            return isExist;
        }
        return isExist;

    }

    public User getLogedUser(String email) throws SQLException {
        User user = new User();
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String loginRs = rs.getString("email");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String pwdRs = rs.getString("password");
            String roles = rs.getString("roles");
            int ageRs = rs.getInt("age");
            user = new User(id, loginRs, nom, prenom, pwdRs, roles, ageRs);

        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("email");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String pwd = resultSet.getString("password");
                String roles = resultSet.getString("roles");
                int ageRs = resultSet.getInt("age");
                User user = new User(id, login, nom, prenom, pwd, roles, ageRs);
                userList.add(user);
            }
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(userList);
        return userList;
    }

    public Boolean isValidCredentials(String email, String password) throws SQLException {

        Boolean isValid = false;
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String pwdRs = rs.getString("password");
            if (PasswordHash.verifyPassword(password, pwdRs)) {
                isValid = true;
                return isValid;

            }
        }
        return isValid;

    }

    public Boolean login(String email, String password) throws SQLException {
        return isValidCredentials(email, password);
    }

    public void updateEntitee(User user, String x, String y) {
        try {
            String requete = "UPDATE user SET email = ?,password = ?, WHERE email =" + user.getEmail();
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, user.getEmail());
            pst.setString(2, PasswordHash.hashPassword(user.getPassword()));
            
           
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    


}