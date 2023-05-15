/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

import edu.fithnitek.entities.Bien;
import edu.fithnitek.entities.CalculeDistance;
import edu.fithnitek.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadhem
 */
public class BienCRUD {
        private final Connection cnx;
        
        public BienCRUD(){
            cnx = MyConnection.getInstance().getCnx();
        }
        
       /* public void create(Bien t){
            try {
                String requete = "INSERT INTO Bien(lieud, lieua, dated, num)"+ "values(?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx()
                        .prepareStatement(requete);
                pst.setString(1,t.getLieud());
                pst.setString(2,t.getLieua());
                pst.setDate(3,t.getDated());
                pst.setString(4,t.getNum());
                pst.executeUpdate();
                System.out.println("Bien ajouté");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
        
        public void update(Bien t,String ld,String la,Date d,String num){
            try {
                String requete = "update Bien set lieud=?, lieua=?, dated=?, num=? where id="+t.getId()+"";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, t.getLieud());
                pst.setString(2, t.getLieua());
                pst.setObject(3, t.getDated());
                pst.setString(4, t.getNum());
                pst.executeUpdate();
                System.out.println("fx");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        public void delete(Bien t){
            
            try {
                String requete = "DELETE FROM Bien WHERE id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx()
                        .prepareStatement(requete);
                pst.setInt(1, t.getId());
                int row = pst.executeUpdate();
                if (row == 1) {
                    System.out.println("Bien supprimé");
                } else {
                    System.out.println(row);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(BienCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public List<Bien> sellectALL() {
            List<Bien> myList = new ArrayList<>();
            try {
                String requete = "SELECT * FROM Bien";
                Statement st = MyConnection.getInstance().getCnx()
                        .createStatement();
                ResultSet rs = st.executeQuery(requete);
                while (rs.next()){
                    Bien b = new Bien();
                    b.setId(rs.getInt(1));
                    b.setLieud(rs.getString(2));
                    b.setLieua(rs.getString(3));
                    b.setDated(rs.getDate(4));
                    b.setNum(rs.getString(5));
                    double latdepart = rs.getDouble("latitude_depart");
                    double londepart = rs.getDouble("longitude_depart");
                    double latarriver = rs.getDouble("latitude_arrivee");
                    double lonarriver = rs.getDouble("longitude_arrivee");
                    double distance = CalculeDistance.distance(latdepart, londepart, latarriver, lonarriver);
            
                    myList.add(b);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BienCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return myList;
        }
        
        
        /*    public List<Bien> readAll() {
        String requete = "SELECT user.*, role.id_role, role.type_role FROM user INNER JOIN role ON user.id_role = role.id_role";
        List<Bien> list = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Bien t;
                t = new Bien(rs.getInt("id"), rs.getString("lieud"), rs.getString("lieua"), rs.getDate("dated")rs.getInt("num"));
                list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BienCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }*/
        

    
}
