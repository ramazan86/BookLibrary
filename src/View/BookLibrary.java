/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Database.Verbindung;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;

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
        JTextField username = new JTextField();
        
        String sqlSelect = "Insert into login(name,surname,password) VALUES ('abc','def',12);";
        //st.executeQuery(sqlSelect);
        ResultSet myRs= st.executeQuery("select * from login");
        String sql = "INSERT INTO Registration " +
                   "VALUES ("+ username.getText()+", 'Zara', 'Ali', 18)";
        String sql2 = "INSERT INTO registry (username,password,Email,birthday,prename,surname,street,postalcode, city,iban,bic)VALUES ('Ali','uuuu', 'aa@dd.de', '1972-11-03', 'ali','ali','belinerstrasse','55122','Mainz','6666666','1234');";
        st.execute(sqlSelect);
        st.execute(sql2);
      
        
        
    }
    
}
