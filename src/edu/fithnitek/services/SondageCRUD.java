/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

import edu.fithnitek.interfaces.InterfaceCRUD;
import edu.fithnitek.entities.Questions;
import edu.fithnitek.entities.Réponses;
import edu.fithnitek.entities.Sondage;
import edu.fithnitek.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author user
 */
public class SondageCRUD implements InterfaceCRUD<Sondage>  {
   

    @Override
    public void ajouterEntitee(Sondage t) {
        try {
            String req = "insert into Sondage (sujet,categorie) values"
                    + " ( '" + t.getSujet() + "', '" + t.getCategorie() + "')";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
           ps.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(Sondage t) {
        try {
            String req = "update Sondage set sujet = ? , categorie = ?  where sondage_id = ?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, t.getSujet());
            ps.setString(2, t.getCategorie());
            ps.setInt(3, t.getSondage_id());
           
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

 
    public void supprimer(int sondage_id) {
        try {
            String req = "delete from Sondage where sondage_id = ?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, sondage_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Sondage> listeDesEntites() {
        ObservableList<Sondage> list = FXCollections.observableArrayList();
        try {
            String req ="select * from Sondage";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){
                Sondage s = new Sondage();
                s.setSondage_id(rs.getInt(1));
                s.setSujet(rs.getString("sujet"));
                s.setCategorie(rs.getString("categorie"));
                
               
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Sondage get(String S) {
        Sondage so = new Sondage();
        
        try {
            String req ="select * from Sondage where Upper(Sondage.Sujet)= '"+S.toUpperCase()+"'";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            
            while (rs.next()){
            so.setSondage_id(rs.getInt("Sondage_id"));
            so.setSujet(rs.getString("Sujet"));
            so.setCategorie(rs.getString("Categorie"));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }
    
    public int nbrs(String s) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from Sondage where Upper(sujet)= '"+s.toUpperCase()+"'";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
             ResultSet rs = ps.executeQuery(req);
             while (rs.next()){    
                x=rs.getInt("n");
                System.out.println(x);
               }
             } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
             }
        return x;
    }
      public ObservableList<String> afficherSujet() {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String req ="select Sujet from Sondage";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){
              //  Sondage s = new Sondage();
                
                list.add(rs.getString("sujet"));
                
                
               
              //  list.add(s.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      
      public int getid(String S) {
        Sondage so = new Sondage();
        int id=0; 
        try {
            String req ="select sondage_id s from Sondage where Upper(Sondage.Sujet)= '"+S.toUpperCase()+"'";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
                       System.out.println(rs);

            while (rs.next()){
           id=rs.getInt("s");
            
            
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SondageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    
    
     
}
