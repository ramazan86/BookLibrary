/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary;

import db_connection.Verbindung;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Asus
 */
public class BookLibrary {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        Verbindung vb = new Verbindung();
        Statement st = vb.getStatement();
        
        String sqlSelect = "Insert into login(name,surname,password) VALUES ('abc','def',12);";
        //st.executeQuery(sqlSelect);
        st.execute(sqlSelect);
      
        
        
    }
    
}
