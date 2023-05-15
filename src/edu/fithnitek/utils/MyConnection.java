/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author karra
 */
public class MyConnection {
    String url="jdbc:mysql://localhost:3306/fithnitek5";
    String login="root";
    String pwd="";
    private Connection cnx;
    private static MyConnection instance;

    private MyConnection() {
        try {
         cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
         return instance;
    }
    
    
    
}
