/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

import edu.fithnitek.entities.OffreCovoiturage;
import edu.fithnitek.entities.demandecovoiturage;
import edu.fithnitek.interfaces.InterfaceCRUD;
import edu.fithnitek.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**

 
 */
public class reservationCRUD implements InterfaceCRUD<demandecovoiturage> {

    @Override
    public void ajouterEntitee(demandecovoiturage t) {
       
        String req = "insert into demandecovoiturage (dateReservation,nbPlace,id_offre) values"
                + " ( '" + t.getDateReservation() + "', '" + t.getNbplace() + "', '" + t.getOffre().getId()+ "')";
        try {
     Statement ps = MyConnection.getInstance().getCnx().createStatement();
            ps.executeUpdate(req);
            System.out.println("done!");
        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override

    public ObservableList<demandecovoiturage> listeDesEntites() {
        ObservableList<demandecovoiturage> list = FXCollections.observableArrayList();

        try {

            String requete = " SELECT * FROM demandecovoiturage , offrecovoiturage where demandecovoiturage.id_offre=offrecovoiturage.id";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                OffreCovoiturage o = new OffreCovoiturage();
               // demandecovoiturage p = new demandecovoiturage();
               demandecovoiturage p = new demandecovoiturage(o);
                p.setId(rs.getInt(1));
                p.setDateReservation(rs.getDate(2));          //   p.setNom(rs.getString("nom"));
                p.setNbplace(rs.getInt(3));
                p.getOffre().setId(rs.getInt("offrecovoiturage.id"));
                p.getOffre().setMatricule(rs.getString("offrecovoiturage.matricule"));
                p.getOffre().setMarque(rs.getString("offrecovoiturage.marque"));
                p.getOffre().setDateD(rs.getDate("offrecovoiturage.dateD"));
                p.getOffre().setLieuD(rs.getString("offrecovoiturage.lieuD"));
                p.getOffre().setLieuA(rs.getString("offrecovoiturage.lieuA"));
                p.getOffre().setDispo(rs.getString("offrecovoiturage.dispo"));
                p.getOffre().setNbPlace(rs.getInt("offrecovoiturage.nbPlace"));
                p.getOffre().setNumTel(rs.getString("offrecovoiturage.numTel"));
                //p.getOffre().setIdUsr(rs.getInt("offrecovoiturage.id_usr"));
                //p.getOffre().setIdAvis(rs.getInt("offrecovoiturage.id_avis"));
                list.add(p);
                
                
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void modifier(demandecovoiturage t) {

        try {
            String req = "update demandecovoiturage set dateReservation = ? , nbplace = ? , id_offre = ? where id= ? ";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ps.setInt(4, t.getId());
            ps.setDate(1, t.getDateReservation());
            ps.setInt(2, t.getNbplace());
            ps.setInt(3, t.getOffre().getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void supprimer(demandecovoiturage dc) {

        try {
            String requete = "delete from demandecovoiturage where id ="+dc.getId();
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            //ps.setInt(1, id);
              ps.executeUpdate(requete);
        } catch (SQLException ex) {
            
            
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }
     public demandecovoiturage getIdDC(int id) {
        demandecovoiturage dc = new demandecovoiturage();
        OffreCovoiturage oc = new OffreCovoiturage();
        
        try {
            String req ="select * from demandecovoiturage where demandecovoiturage.id_offre= '"+ id +"'";
             Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                dc.setId(rs.getInt(1));
                dc.setDateReservation(rs.getDate(2));          //   p.setNom(rs.getString("nom"));
                dc.setNbplace(rs.getInt(3));
              //  dc.getOffre().setId(rs.getInt(4));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dc;
    }
     
     
      public OffreCovoiturage getIdDO(int id) {
       // demandecovoiturage dc = new demandecovoiturage();
        OffreCovoiturage oc = new OffreCovoiturage();
        
        try {
            String req ="select * from offrecovoiturage where offrecovoiturage.id= '"+ id +"'";
             Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                oc.setId(rs.getInt(1));
            oc.setMatricule(rs.getString(2));
            oc.setMarque(rs.getString(3));
            oc.setDateD(rs.getDate(4));
            oc.setLieuD(rs.getString(5));
            oc.setLieuA(rs.getString(6));
            oc.setDispo(rs.getString(7));
            oc.setNbPlace(rs.getInt(8));
            oc.setNumTel(rs.getString(9));
            //oc.setIdUsr(rs.getInt(10));
            //oc.setIdAvis(rs.getInt(11));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oc;
    }


}

