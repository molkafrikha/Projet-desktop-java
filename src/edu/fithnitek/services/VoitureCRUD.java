/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Voiture;
import edu.fithnitek.entities.maintenance;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class VoitureCRUD    {
    
    
   
    public void  add (Voiture v){
        
        
        try {
            String sql="INSERT INTO voiture (id , matricule , marque , puissance, kilometrage, nbplaces, dateAssurance , dateDVidange )"+ "VALUES (?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setInt(1, v.getId_voiture());
            pst.setString(2, v.getMatricule());
            pst.setString(3, v.getMarque());
            pst.setString(4, v.getPuissance());
            pst.setInt(5, v.getKilometrage());
            pst.setInt(6, v.getNbplaces());
            pst.setDate(7, (Date) v.getDateAssurance());
            pst.setDate(8, (Date) v.getDateDVidange());
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        
        
    }
    
     public void  addM (maintenance M){
        
        
        try {
            String sql="INSERT INTO maintenance (id_maintenance, matricule ,dateDAssurance,datePAssurance, dateDVidange )"+ "VALUES (?,?,?,?,?)";
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            
            pst.setInt(1, M.getId_maintenance());
            pst.setString(2, M.getMatricule());
            pst.setDate(3,(Date) M.getDateDAssurance());
            pst.setDate(4,(Date) M.getDatePAssurance());
            pst.setDate(5,(Date) M.getDateDVidange());
            
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
        
        
    }
    
      
        
     
        
        
    public List<Voiture> getAll() {
    List<Voiture> listVoitures = new ArrayList<>();

    try {
        
        String query = "SELECT id , matricule , marque , puissance, kilometrage, nbplaces, dateAssurance , dateDVidange , color FROM voiture";
        ResultSet resultSet;
        try (Statement statement = MyConnection.getInstance().getCnx().createStatement()) {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Voiture v = new Voiture(resultSet.getInt("id"), resultSet.getString("matricule"),
                        resultSet.getString("marque"), resultSet.getString("puissance"),
                        resultSet.getInt("kilometrage"), resultSet.getInt("nbplaces"),
                        resultSet.getDate("dateAssurance"), resultSet.getDate("dateDVidange") , resultSet.getString("color") );
                
                // System.out.println(v.toString());
                listVoitures.add(v);
            }
        }
        resultSet.close();
        
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des voitures : " + ex.getMessage());
    }

    return listVoitures;
}
    
    
     public List<maintenance> getmaintenance() {
    List <maintenance> listM = new ArrayList<>();

    try {
        
        String query = "SELECT  id_maintenance ,  matricule , dateDAssurance , datePAssurance,dateDVidange, restekilometre FROM maintenance";
        ResultSet resultSet;
        try (Statement statement = MyConnection.getInstance().getCnx().createStatement()) {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                maintenance m = new maintenance(resultSet.getInt("id_maintenance"), resultSet.getString("matricule"),
                        resultSet.getDate("dateDAssurance"), resultSet.getDate("datePAssurance"),
                        resultSet.getDate("dateDVidange"),resultSet.getInt("restekilometre")
                       );
                
                 //System.out.println(m.toString());
                listM.add(m);
            }
        }
        resultSet.close();
        
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des maintenance: " + ex.getMessage());
    }

    return listM;
}
    
    public List<Voiture> getPart() {
    List<Voiture> listMaintenance = new ArrayList<>();

    try {
        
        String query = "SELECT matricule ,dateAssurance , dateDVidange  FROM voiture";
        ResultSet resultSet;
        try (Statement statement = MyConnection.getInstance().getCnx().createStatement()) {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Voiture v = new Voiture( resultSet.getString("matricule"),
                        
                       resultSet.getDate("dateAssurance"), resultSet.getDate("dateDVidange"));
                
                // System.out.println(v.toString());
                listMaintenance.add(v);
            }
        }
        resultSet.close();
        
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des voitures : " + ex.getMessage());
    }

    return listMaintenance;
}
    
    
    
    
        
  

   
    
    } 
        
        
      

        
    



    
    
    
    
    

