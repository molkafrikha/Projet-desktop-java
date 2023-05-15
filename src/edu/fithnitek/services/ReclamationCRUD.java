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
import edu.fithnitek.entities.Reclamation;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.interfaces.InterfaceCRUDN;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Nihel
 */
public class ReclamationCRUD implements InterfaceCRUDN<Reclamation> {

    private Connection cnx;

    public ReclamationCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void create(Reclamation t) {
        try {
            String requete = "INSERT INTO reclamation(intitule, contenu, idUser,date,image)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getIntitule());
            pst.setString(2, t.getContenu());
            pst.setInt(3, 55);
            pst.setDate(4,Date.valueOf(LocalDate.now()));
            pst.setString(5, "images/attachments/cov.jpg");
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        int rslt = 0;

        try {

            String request = "DELETE FROM reclamation WHERE id ='" + id + "'";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);

            rslt = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rslt == 1;
    }

    @Override
    public List<Reclamation> selectAll() {

        List<Reclamation> reclamations = new ArrayList();

        try {
            String request = "SELECT * FROM reclamation";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);

            ResultSet rs = pst.executeQuery(request);

            while (rs.next()) {

                Reclamation r = new Reclamation();

                r.setId(rs.getInt(1));
                r.setIntitule(rs.getString(2));
                r.setContenu(rs.getString(3));
                r.setId_usr(rs.getInt(4));
                r.setDate(rs.getDate(5));
                reclamations.add(r);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        return reclamations;
    }

    @Override
    public int count() throws SQLException {
         int count = 0;
        try {
            String request = "SELECT COUNT(*) FROM reclamation";
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


