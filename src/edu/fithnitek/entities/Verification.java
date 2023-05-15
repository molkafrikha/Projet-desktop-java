/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;

/**
 *
 * @author T480s
 */
public class Verification {

    public Verification(int id, String code) {
        this.id = id;
        this.code = code;
    }

 
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
      
      @Override
    public String toString() {
        return "Verification{" + "id=" + id + ", code=" + code + '}';
    } 
}
