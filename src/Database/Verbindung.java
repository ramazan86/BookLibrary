/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;



/**
 * This class 
 * @author Ramazan Cinardere & Ali Hannoun
 */
public class Verbindung {
    
    /*####################
     *#    Attributes    #
     *###################*/
    
    private Connection cn = null;
    private Statement st  = null;
    
    /**
     * 
     */
    public Verbindung() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklibrary","root","");
            st = cn.createStatement();
            System.out.println("Connection Succes");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    
    
    }//Verbindung()
    
    public Connection getConnection() {
        return this.cn;
    }
    
    public Statement getStatement() {
        return this.st;
    }
    
    
}
