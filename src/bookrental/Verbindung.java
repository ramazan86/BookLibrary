package bookrental;

import com.mysql.jdbc.Connection;   
import java.sql.*;
//

public class Verbindung {
   public Connection con;
   
    public void start(){
        try{
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/booklibrary","root","");
        }catch(Exception e){
            System.out.println("Verbindung zur Datenbank fehlgeschlagen!!" + e);
        }
    }//start Methode closing
    
    public Connection getVerbindung(){
        return con;
    }//getVerbindung closing
    
    public boolean closeDB() throws SQLException {
    
        if(con != null) {
            con.close();
            return true;
        }
        return false;
    }
   
}
  