/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;


import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Nadhem
 */
public class Bien {
    private int id;
    private String lieud;
    private String lieua;
    private Date dated;
    private String num;

    public Bien() {
    }

    public Bien(int id, String lieud, String lieua, Date dated, String num) {
        this.id = id;
        this.lieud = lieud;
        this.lieua = lieua;
        this.dated = dated;
        this.num = num;
    }

    public Bien(String lieud, String lieua, Date dated, String num) {
        this.lieud = lieud;
        this.lieua = lieua;
        this.dated = dated;
        this.num = num;
    }

    

    public int getId() {
        return id;
    }

    public String getLieud() {
        return lieud;
    }

    public String getLieua() {
        return lieua;
    }

    public Date getDated() {
        return dated;
    }

    public String getNum() {
        return num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLieud(String lieud) {
        this.lieud = lieud;
    }

    public void setLieua(String lieua) {
        this.lieua = lieua;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Bien{" + "id=" + id + ", lieud=" + lieud + ", lieua=" + lieua + ", dated=" + dated + ", num=" + num + '}';
    }
    
    
    
}
