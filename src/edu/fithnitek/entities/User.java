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
public class User {
    private int id;
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private String  roles;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(){};
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
   
    

    public User(int id,  String nom, String prenom,  String email, String password,String roles, int age) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.roles = roles;
        this.age = age;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPwd(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", roles=" +roles  + ", age=" + age + '}';
    }

   

    
 
    
    
    
    
}