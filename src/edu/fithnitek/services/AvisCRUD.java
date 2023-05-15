/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.fithnitek.entities.Avis;
import edu.fithnitek.entities.Reclamation;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.interfaces.InterfaceCRUDN;

/**
 *
 * @author Nihel
 */
public class AvisCRUD implements InterfaceCRUDN <Avis>{
    private Connection cnx ;
    
    public AvisCRUD()
    {
        cnx= MyConnection.getInstance().getCnx();
    }

    @Override
    public void create(Avis t) {
        try {
            String requete = "INSERT INTO avis(commenraire, idUser)"
                    + " VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                                    .prepareStatement(requete);
            pst.setString(1, t.getCommentaire());
            pst.setInt(2, t.getId_usr());
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public boolean delete(int id) {
        int rslt = 0 ;
            
        try {
            
            String request="DELETE FROM avis WHERE id ='"+id+"'";
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            
            rslt=pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return rslt==1;
    }

    @Override
    public List<Avis> selectAll() {
         List<Avis> avis = new ArrayList();
        
        try {
            String request = "SELECT * FROM avis";
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs=  pst.executeQuery(request);
            
            while(rs.next()){
                
                Avis a = new Avis();
                
                a.setId(rs.getInt(1));
                a.setCommentaire(rs.getString(2));
                a.setId_usr(rs.getInt(3));
                //a.setId_offre(rs.getInt(4));
                
                avis.add(a);
                
            }
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
        }
       return avis;
    }

    @Override
    public int count() throws SQLException {
         int count = 0;
        try {
            String request = "SELECT COUNT(*) FROM avis";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            ResultSet rs = pst.executeQuery(request);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return count;
    }

   
    
}
