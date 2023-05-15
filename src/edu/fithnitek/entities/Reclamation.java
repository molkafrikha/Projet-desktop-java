/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;

import java.sql.Date;
import java.time.LocalDate;


/**
 *
 * @author Nihel
 */
public class Reclamation {
    private int id;
    private String intitule;
    private String contenu;
    private int id_usr;
    private Date date;
    private String image;
    public Reclamation() {
    }

    public Reclamation(int id, String intitule, String contenu, int id_usr,Date date,String image) {
        this.id = id;
        this.intitule = intitule;
        this.contenu = contenu;
        this.id_usr = id_usr;
        this.date=date;
        this.image=image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Reclamation(String intitule, String contenu, int id_usr) {
        this.intitule = intitule;
        this.contenu = contenu;
        this.id_usr = id_usr;
    }

    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getContenu() {
        return contenu;
    }

     public Date getDate() {
        return date;
    }
    
    public int getId_usr() {
        return id_usr;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", intitule=" + intitule + ", contenu=" + contenu + ", id_usr =" + id_usr+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

}
