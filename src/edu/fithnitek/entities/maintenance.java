/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;


import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
/**
 *
 * @author USER
 */
public class maintenance {
    
    private int id_maintenance ;
    private String matricule;
    private Date dateAssurance;
    private Date datePAssurance;
    private Date dateDVidange;
    private Date datePVidange;
    private int restekilometre;

    public maintenance (int id_maintenance, String matricule, Date dateAssurance, Date datePAssurance, Date dateDVidange, int restekilometre) {
        this.id_maintenance = id_maintenance;
        this.matricule=matricule;
        this.dateAssurance = dateAssurance;
        this.datePAssurance = datePAssurance;
        this.dateDVidange = dateDVidange;
        
        this.restekilometre = restekilometre;
        
    }

   

    public int getRestekilometre() {
        return restekilometre;
    }

    public void setRestekilometre(int restekilometre) {
        this.restekilometre = restekilometre;
    }
    
    public maintenance(int id_maintenance, String matricule, Date dateAssurance, Date datePAssurance, Date dateDVidange) {
        this.id_maintenance = id_maintenance;
        this.matricule=matricule;
        this.dateAssurance = dateAssurance;
        this.datePAssurance = datePAssurance;
        this.dateDVidange = dateDVidange;
        
    }

   

    public void setId_maintenance(int id_maintenance) {
        this.id_maintenance = id_maintenance;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setDateAssurance(Date dateAssurance) {
        this.dateAssurance = dateAssurance;
    }

    public int getId_maintenance() {
        return id_maintenance;
    }

    public String getMatricule() {
        return matricule;
    }

    public Date getDateAssurance() {
        return dateAssurance;
    }

    public void setDatePAssurance(Date datePAssurance) {
        this.datePAssurance = datePAssurance;
    }

    public void setDateDVidange(Date dateDVidange) {
        this.dateDVidange = dateDVidange;
    }

    public void setDatePVidange(Date datePVidange) {
        this.datePVidange = datePVidange;
    }

   

    public Date getDateDAssurance() {
        return dateAssurance;
    }

    public Date getDatePAssurance() {
        return datePAssurance;
    }

    public Date getDateDVidange() {
        return dateDVidange;
    }

    public Date getDatePVidange() {
        return datePVidange;
    }


    }

 
    
    
    
