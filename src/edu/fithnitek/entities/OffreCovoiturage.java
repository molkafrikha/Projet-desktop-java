package edu.fithnitek.entities;

import java.sql.Date;
import java.util.List;

public class OffreCovoiturage {





    private int id;
    private String matricule;
    private String marque;
    private Date dateD;
    private String lieuD;
    private String lieuA;
    private String dispo;
    private int nbPlace;
    private String numTel;
    private double distance;
    private String depart;
    private String arriver;
    private float latdepart;
    private float latarriver;
    private float londepart;
    private float lonarriver;

    public OffreCovoiturage() {

    }

    public OffreCovoiturage(String matricule, String marque, Date dateD, String lieuD, String lieuA, String dispo, int nbPlace, String numTel, double distance) {
        this.matricule = matricule;
        this.marque = marque;
        this.dateD = dateD;
        this.lieuD = lieuD;
        this.lieuA = lieuA;
        this.dispo = dispo;
        this.nbPlace = nbPlace;
        this.numTel = numTel;
        
    }
    

    public OffreCovoiturage(int id, String matricule, String marque, Date dateD, String lieuD, String lieuA, String dispo, int nbPlace, String numTel, double distance) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.dateD = dateD;
        this.lieuD = lieuD;
        this.lieuA = lieuA;
        this.dispo = dispo;
        this.nbPlace = nbPlace;
        this.numTel = numTel;
        
    }

    

    //public OffreCovoiturage(String text, String text0, String text1, String text2, String text3, String text4, String text5, String text6) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public OffreCovoiturage(int id, String matricule, String marque, Date dateD, String lieuD, String lieuA, String dispo, int nbPlace, String numTel) {
    }

    //public OffreCovoiturage(int aInt, String string, String string0, Date date, String string1, String string2, String string3, int aInt0, int aInt1) {
    //}

    public OffreCovoiturage(String matricule, String marque, Date dateD, String lieuD, String lieuA, String dispo, int nbPlace, String numTel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


    public double getDistance() {
        return distance;
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public Date getDateD() {
        return dateD;
    }

    public String getLieuD() {
        return lieuD;
    }

    public String getLieuA() {
        return lieuA;
    }

    public String isDispo() {
        return dispo;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public String getNumTel() {
        return numTel;
    }



    
    
    
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public void setLieuD(String lieuD) {
        this.lieuD = lieuD;
    }

    public void setLieuA(String lieuA) {
        this.lieuA = lieuA;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "OffreCovoiturage{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", dateD=" + dateD + ", lieuD=" + lieuD + ", lieuA=" + lieuA + ", dispo=" + dispo + ", nbPlace=" + nbPlace + ", numTel=" + numTel + ", distance=" + distance + ", depart=" + depart + ", arriver=" + arriver + ", latdepart=" + latdepart + ", latarriver=" + latarriver + ", londepart=" + londepart + ", lonarriver=" + lonarriver + '}';
    }







}
