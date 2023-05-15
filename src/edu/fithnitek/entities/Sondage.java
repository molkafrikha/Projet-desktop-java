/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;

/**
 *
 * @author SAHBI
 */
public class Sondage {
     private int sondage_id ;
    private String sujet;
    private String categorie;

    public Sondage() {
    }

    public Sondage(int sondage_id, String sujet, String categorie) {
        this.sondage_id = sondage_id;
        this.sujet = sujet;
        this.categorie = categorie;
    }

    public Sondage(String sujet, String categorie) {
        this.sujet = sujet;
        this.categorie = categorie;
    }

    public int getSondage_id() {
        return sondage_id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setSondage_id(int sondage_id) {
        this.sondage_id = sondage_id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Sondage{" + "sondage_id=" + sondage_id + ", sujet=" + sujet + ", categorie=" + categorie + '}';
    }
    
    
    
}