/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;

import java.sql.Date;

/**
 *
 * @author SAHBI
 */
public class demandecovoiturage {
    private int id;
    private  Date dateReservation;
    private  int  nbplace;
    private int id_offre;
    private OffreCovoiturage offre;

    public demandecovoiturage(OffreCovoiturage offre) {
        this.offre = offre;
    }

    public demandecovoiturage(Date dateReservation, int nbplace, OffreCovoiturage offre) {
        this.dateReservation = dateReservation;
        this.nbplace = nbplace;
        this.offre = offre;
    }

    public demandecovoiturage(int id, int nbplace) {
        this.id = id;
        this.nbplace = nbplace;
    }

    public demandecovoiturage(Date dateReservation, int nbplace, int id_offre) {
        this.dateReservation = dateReservation;
        this.nbplace = nbplace;
        this.id_offre = id_offre;
    }

    public void setOffre(OffreCovoiturage offre) {
        this.offre = offre;
    }

    public OffreCovoiturage getOffre() {
        return offre;
    }

    public demandecovoiturage() {
    }

    public demandecovoiturage(int id, Date dateReservation, int nbplace, int id_offre) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.nbplace = nbplace;
        this.id_offre = id_offre;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

 
    
       public demandecovoiturage(Date dateReservation,int nbplace) {
        this.dateReservation = dateReservation;
        this.nbplace = nbplace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int  getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    @Override
    public String toString() {
        return "demandecovoiturage{" + "id=" + id + ", dateReservation=" + dateReservation + ", nbplace=" + nbplace + '}';
    }

   
}
