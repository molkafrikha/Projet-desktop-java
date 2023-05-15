package edu.fithnitek.services;

import edu.fithnitek.entities.CalculeDistance;
import static edu.fithnitek.entities.CalculeDistance.distance;
import edu.fithnitek.entities.OffreCovoiturage;
import edu.fithnitek.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OffreCovoiturageCRUD extends CalculeDistance{

    private final Connection cnx;

    public OffreCovoiturageCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

 /*   public void create(OffreCovoiturage t) {
        try {
            String requete = "INSERT INTO OffreCovoiturage(matricule, marque, dateD, lieuD, lieuA, dispo, nbPlace, numTel, id_Demande)"
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getMatricule());
            pst.setString(2, t.getMarque());
            pst.setDate(3, t.getDateD());
            pst.setString(4, t.getLieuD());
            pst.setString(5, t.getLieuA());
            pst.setString(6, t.isDispo());
            pst.setInt(7, t.getNbPlace());
            pst.setString(8, t.getNumTel());
            pst.setInt(9, t.getIdDemande());
           // pst.setDouble(10, t.getDistance());
            pst.executeUpdate();
            System.out.println("Offre ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }*/

  //  public void update(OffreCovoiturage t) {

     //   try {
      //      String requete = "UPDATE OffreCovoiturage SET matricule=?,marque=?,dateD=?,lieuD=?, lieuA=?, dispo=?,"
      //              + "nbPlace=?,numTel=?,id_usr=?,id_avis=?,id_demande=?, distance=?  WHERE id=?";
       //     PreparedStatement pst = MyConnection.getInstance().getCnx()
       //             .prepareStatement(requete);
        //    pst.setString(1, t.getMatricule());
        //    pst.setString(2, t.getMarque());
        //    pst.setDate(3, t.getDateD());
         //   pst.setString(4, t.getLieuD());
         //   pst.setString(5, t.getLieuA());
         //   pst.setString(6, t.isDispo());
         //   pst.setInt(7, t.getNbPlace());
         //   pst.setString(8, t.getNumTel());
            
         //   pst.setInt(9, t.getIdDemande());
        //    pst.setInt(10, t.getId());
           // pst.setDouble(11, t.getDistance());
        //    pst.executeUpdate();
        //    System.out.println("Offre modifié");
     //   } catch (SQLException ex) {
       //     Logger.getLogger(OffreCovoiturageCRUD.class.getName()).log(Level.SEVERE, null, ex);
    //    }

  //}

    public void delete(OffreCovoiturage t) {
        String requete = "DELETE FROM offrecovoiturage WHERE id=?";
       try {
          
           PreparedStatement pst = MyConnection.getInstance().getCnx()
                   .prepareStatement(requete);
           pst.setInt(1, t.getId());
           int row = pst.executeUpdate();
           if (row == 1) {
               System.out.println("Offre supprimé");
            } else {
                System.out.println(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreCovoiturageCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<OffreCovoiturage> selectAll() {
    List<OffreCovoiturage> myList = new ArrayList<>();
    try {
        String requete = "SELECT * FROM OffreCovoiturage";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            OffreCovoiturage oc = new OffreCovoiturage();
            oc.setId(rs.getInt(1));
            oc.setMatricule(rs.getString(2));
            oc.setMarque(rs.getString(3));
            oc.setDateD(rs.getDate(4));
            oc.setLieuD(rs.getString(5));
            oc.setLieuA(rs.getString(6));
            oc.setDispo(rs.getString(7));
            oc.setNbPlace(rs.getInt(8));
            oc.setNumTel(rs.getString(9));
            
            double latdepart = rs.getDouble("latitude_depart");
            double londepart = rs.getDouble("longitude_depart");
            double latarriver = rs.getDouble("latitude_arrivee");
            double lonarriver = rs.getDouble("longitude_arrivee");
            double distance = CalculeDistance.distance(latdepart, londepart, latarriver, lonarriver);
            oc.setDistance(rs.getDouble(10));
            
            
            myList.add(oc);
        }
    } catch (SQLException ex) {
        Logger.getLogger(OffreCovoiturageCRUD.class.getName()).log(Level.SEVERE, null, ex);
    }
    return myList;
}

        
        
     
    

    //public List<OffreCovoiturage> getAllOffreCovoiturage() {
       // List<OffreCovoiturage> OffreCovoiturageList = new ArrayList<>();
       // try {
         //   String requete = "SELECT * FROM OffreCovoiturage";
         //   PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
          //  ResultSet resultSet = pst.executeQuery();
          //  while (resultSet.next()) {
          //      int id = resultSet.getInt("id");
           //     String matricule = resultSet.getString("matricule");
           //     String marque = resultSet.getString("marque");
            //    Date dateD = resultSet.getDate("dateD");
             //   String lieuD = resultSet.getString("lieuD");
             //   String lieuA = resultSet.getString("lieuA");
             //   String dispo = resultSet.getString("dispo");
             //   int nbPlace = resultSet.getInt("nbPlace");
             //   String numTel = resultSet.getString("numTel");
             //   int idDemande = resultSet.getInt("idDemande");
            //    double distance = resultSet.getDouble("distance");

              //  OffreCovoiturage oc = new OffreCovoiturage (id, matricule, marque, dateD, lieuD, lieuA, dispo, nbPlace, numTel, idDemande, distance);

             //   OffreCovoiturageList.add(oc);
            //}
          //  System.out.println("Done!");
      //  } catch (SQLException ex) {
        //    System.out.println(ex.getMessage());
       // }
       // System.out.println(OffreCovoiturageList);
      //  return OffreCovoiturageList;
   // }

   // public List<OffreCovoiturage> readAll() {
    //    String requete = "SELECT user.*, role.id_role, role.type_role FROM user INNER JOIN role ON user.id_role = role.id_role";
    //    List<OffreCovoiturage> list = new ArrayList<>();
     //   try {
       //     Statement st = cnx.createStatement();
      //      ResultSet rs = st.executeQuery(requete);
        //    while (rs.next()) {

         //       OffreCovoiturage t;
         //       t = new OffreCovoiturage(rs.getInt("id"), rs.getString("matricule"), rs.getString("marque"), rs.getDate("dateD"), rs.getString("lieuD"), rs.getString("lieuA"), rs.getString("dispo"), rs.getInt("nbPlace"), rs.getString("numTel"), rs.getInt("idDemande"), rs.getDouble("distance"));
          //      list.add(t);
         //   }
     //   } catch (SQLException ex) {
      //      Logger.getLogger(OffreCovoiturageCRUD.class.getName()).log(Level.SEVERE, null, ex);
     //   }
    //    return list;
   // }
}
